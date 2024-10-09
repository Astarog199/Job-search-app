package com.example.jobsearchapp.ui.home.presently.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.jobsearchapp.R
import com.example.jobsearchapp.databinding.FragmentHomeBinding
import com.example.jobsearchapp.ui.home.presently.list.adapter.HomeAdapter
import com.example.jobsearchapp.ui.home.presently.list.states.HomeState
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = HomeAdapter { state -> onItemClick(state) }

    private val viewModel by viewModels <HomeViewModel>{
        FeatureServiceLocator.provideViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        viewModel.offers()
        viewModel.loadItems()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect {state ->
                    when{
                        state.isLoading -> showLoading()

                        state.hasError -> {
                            showError()
                            viewModel.errorShown()
                        }

                        else -> {
                            showList(state.vacanciesList)
                        }
                    }
                }
            }
        }
    }

    private fun showList(vacanciesList: List<HomeState>) {
        adapter.setData(vacanciesList)
        binding.recyclerView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun showError() {
        Toast.makeText(
            requireContext(),
            "Error wile loading data",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showLoading() {
        binding.recyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun onItemClick(state: HomeState) {
        requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
            .navigate(
                resId = R.id.action_main_to_details,
                args = bundleOf("item" to state.id)
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}