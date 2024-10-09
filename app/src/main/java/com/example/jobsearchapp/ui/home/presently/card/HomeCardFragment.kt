package com.example.jobsearchapp.ui.home.presently.card

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.jobsearchapp.databinding.FragmentHomeCardBinding
import com.example.jobsearchapp.ui.home.presently.card.states.CardState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeCardFragment : Fragment() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var _binding: FragmentHomeCardBinding? = null
    private val binding get() = _binding!!
    private var itemId = ""

    private val viewModel: HomeCardViewModel by viewModels<HomeCardViewModel> {
        FeatureServiceLocator.provideCardViewModelFactory(itemId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            itemId = it?.getString("item").toString()
        }

        viewModel.loadCard()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when {
                        state.isLoading -> renderLoading()

                        state.hasError -> {
                            showError()
                            viewModel.clearError()
                        }

                        else -> renderCard(state.card)
                    }
                }
            }
        }
    }

    private fun renderCard(state: CardState) {
        binding.title.text = state.title

        binding.progress.visibility = View.GONE
    }

    private fun renderLoading() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun showError() {
        Toast.makeText(
            requireContext(),
            "Error wile loading data",
            Toast.LENGTH_SHORT
        ).show()
    }
}