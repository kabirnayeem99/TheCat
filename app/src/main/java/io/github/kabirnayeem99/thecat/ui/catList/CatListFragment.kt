package io.github.kabirnayeem99.thecat.ui.catList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.kabirnayeem99.thecat.R
import io.github.kabirnayeem99.thecat.common.base.BaseFragment
import io.github.kabirnayeem99.thecat.databinding.FragmentCatListBinding
import kotlinx.coroutines.launch

private const val TAG = "CatListFragment"

@AndroidEntryPoint
class CatListFragment : BaseFragment<FragmentCatListBinding>() {
    override val layout: Int
        get() = R.layout.fragment_cat_list

    private val catAdapter: CatAdapter by lazy {
        CatAdapter()
    }

    private val catViewModel: CatViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        subscribeQuery()
    }

    private fun setUpViews() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCatList.apply {
            adapter = catAdapter
            layoutManager = gridLayoutManager
        }
    }

    private fun subscribeQuery() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                catViewModel.getCatList()
                catViewModel.catListUiState.collect {
                    handleUiState(it)
                }
            }
        }
    }

    private suspend fun handleUiState(uiState: CatListUiState) {
        uiState.apply {
            if (isLoading)
                binding.pbLoading.visibility = View.VISIBLE else binding.pbLoading.visibility =
                View.GONE
            if (message.isNotEmpty())
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            catAdapter.submitData(catPagingDat)
        }
    }
}