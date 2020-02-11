package com.git.williamgdev.fr.ui.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.git.williamgdev.fr.R
import com.git.williamgdev.fr.data.ItemDTO
import com.git.williamgdev.fr.databinding.ItemFragmentBinding
import com.git.williamgdev.fr.ui.item.adapter.ItemListAdapter
import com.git.williamgdev.fr.ui.navigator.ItemNavigator
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

class ItemFragment : Fragment(), ItemNavigator {

    companion object {
        fun newInstance() = ItemFragment()
    }

    private val itemViewModel: ItemViewModel by viewModel()

    private lateinit var binding: ItemFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        itemViewModel.apply {
            itemNavigator = WeakReference(this@ItemFragment)
        }
        binding = ItemFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = itemViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun setupItemRecycler() {
        val itemListAdapter = ItemListAdapter()
        binding.itemRecycler.adapter = itemListAdapter
        itemViewModel.itemList.observe(viewLifecycleOwner, Observer { itemList ->
            itemListAdapter.submitList(itemList)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupItemRecycler()
        itemViewModel.loadItems(requireContext().getString(R.string.fr_api_list_id))
    }

    override fun showError(throwable: Throwable?) {
        view?.let { contextView ->
            Snackbar.make(contextView, throwable?.message ?: "Error Found!", Snackbar.LENGTH_LONG)
                .show()
        }
    }

    override fun displayItems(itemsDTO: List<ItemDTO>?) {
    }

}
