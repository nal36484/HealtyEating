package com.example.healthyeating.presentation.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.healthyeating.R
import com.example.healthyeating.databinding.FragmentMainBinding

class StartFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val startViewModel: StartViewModel by viewModels {
        StartViewModel.StartViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show()
    }

    private fun show() {
        attachListeners()
    }

    private fun attachListeners() {
        binding.addProduct.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_productFragment)
        }
        binding.calculateCalories.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_createEatingListFragment)
        }
        binding.myList.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_myListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}