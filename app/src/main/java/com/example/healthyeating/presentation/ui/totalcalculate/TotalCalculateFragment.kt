package com.example.healthyeating.presentation.ui.totalcalculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthyeating.databinding.FragmentCalculatorBinding
import com.example.healthyeating.presentation.ui.mylist.MyListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TotalCalculateFragment : Fragment() {

    private lateinit var myListAdapter: MyListAdapter
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    private val totalCalculateViewModel: TotalCalculateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myListAdapter = MyListAdapter()

        totalCalculateViewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)

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
        totalCalculateViewModel.products.observe(viewLifecycleOwner) { productsList ->
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