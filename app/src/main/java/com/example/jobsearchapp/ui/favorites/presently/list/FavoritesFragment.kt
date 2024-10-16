package com.example.jobsearchapp.ui.favorites.presently.list

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
import com.example.jobsearchapp.databinding.FragmentFavoritesBinding
import com.example.jobsearchapp.ui.favorites.presently.adapter.FavoriteAdapter
import com.example.jobsearchapp.ui.favorites.presently.list.states.FavoriteState
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoriteAdapter = FavoriteAdapter(
        onClick = { item -> onItemClick(item) },
        favoriteClick = {state -> changeFavoriteState(state)}
    )

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FavoritesViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.applicationContext as App).appComponent
            .favoritesFragmentFactory()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = favoriteAdapter
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
                            showList(state.favoriteList)
                        }
                    }
                }
            }
        }
    }

    private fun showList(favoriteList: List<FavoriteState>) {
        favoriteAdapter.setData(favoriteList)

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

    private fun changeFavoriteState(state: FavoriteState) {
        viewModel.changeFavoriteState(state)
    }

    private fun onItemClick(item: FavoriteState) {
        requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
            .navigate(
                resId = R.id.action_main_to_details,
                args = bundleOf("item" to item.id)
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}