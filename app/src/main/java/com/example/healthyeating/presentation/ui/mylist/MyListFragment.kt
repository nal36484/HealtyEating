package com.example.healthyeating.presentation.ui.mylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthyeating.databinding.FragmentListBinding
import com.example.healthyeating.presentation.ui.utils.LayoutUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyListFragment : Fragment() {

    private lateinit var myListAdapter: MyListAdapter
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val myListViewModel: MyListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myListAdapter = MyListAdapter()

        myListViewModel.getAllProducts()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show()
    }

    private fun show() {
        attachObservers()

        applyAdapters()
    }

    private fun attachObservers() {
        myListViewModel.dataLoading.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> LayoutUtils.crossFade(binding.pbLoading, binding.listLayout)
                false -> LayoutUtils.crossFade(binding.listLayout, binding.pbLoading)
            }
        }

        myListViewModel.products.observe(viewLifecycleOwner) { productsList ->
            myListAdapter.differ.submitList(productsList)
        }
    }

    private fun applyAdapters() {
        binding.rvTotal.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}