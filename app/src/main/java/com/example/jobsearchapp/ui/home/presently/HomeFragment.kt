package com.example.jobsearchapp.ui.home.presently

import android.content.Context
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
import com.example.jobsearchapp.App
import com.example.jobsearchapp.R
import com.example.jobsearchapp.databinding.FragmentHomeBinding
import com.example.jobsearchapp.ui.home.presently.adapter.HomeOffersAdapter
import com.example.jobsearchapp.ui.home.presently.adapter.HomeVacanciesAdapter
import com.example.jobsearchapp.ui.home.presently.states.OffersState
import com.example.jobsearchapp.ui.home.presently.states.VacanciesState
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeVacanciesAdapter = HomeVacanciesAdapter (
        onClick = { item -> onItemClick(item) },
        favoriteClick = {state -> changeFavoriteState(state)}
    )

    private val homeOffersAdapter = HomeOffersAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.applicationContext as App).appComponent
            .homeListFragmentFactory()
            .create()
            .inject(this)
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
        binding.recyclerView.adapter = homeVacanciesAdapter
        binding.recyclerViewForOffers.adapter = homeOffersAdapter

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
                            showList(state.vacanciesList, state.offersList)
                        }
                    }
                }
            }
        }
    }

    private fun showList(vacanciesList: List<VacanciesState>, offersList: List<OffersState>) {
        homeVacanciesAdapter.setData(vacanciesList)
        homeOffersAdapter.setData(offersList)

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

    private fun onItemClick(state: VacanciesState) {
        requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
            .navigate(
                resId = R.id.action_main_to_details,
                args = bundleOf("item" to state.id)
            )
    }
    private fun changeFavoriteState(state: VacanciesState) {
        viewModel.changeFavoriteState(state)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}