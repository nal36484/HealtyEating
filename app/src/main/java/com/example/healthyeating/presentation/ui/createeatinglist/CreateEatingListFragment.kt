package com.example.healthyeating.presentation.ui.createeatinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthyeating.R
import com.example.healthyeating.databinding.FragmentAddForCalculateBinding
import com.example.healthyeating.domain.entities.Dish
import com.example.healthyeating.presentation.ui.utils.setAllReadyExist
import com.example.healthyeating.presentation.ui.utils.setDishNotFound
import com.example.healthyeating.presentation.ui.utils.setFieldIsEmpty
import com.example.healthyeating.presentation.ui.utils.setNameIsEmpty
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateEatingListFragment : Fragment() {

    private lateinit var dishListAdapter: DishListAdapter
    private var _binding: FragmentAddForCalculateBinding? = null
    private val binding get() = _binding!!
    private val createEatingListViewModel: CreateEatingListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dishListAdapter = DishListAdapter(object : OnDeleteClickListener {
            override fun deleteDish(dish: Dish) {
                createEatingListViewModel.deleteDish(dish)
            }
        })

        createEatingListViewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddForCalculateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show()
    }

    private fun show() {
        attachListeners()

        attachObservers()

        applyAdapters()
    }

    private fun attachListeners() {
        binding.calculateCalories.setOnClickListener {
            navigateToCalculateFragment()
        }

        binding.addProduct.setOnClickListener { addDish() }
    }

    private fun attachObservers() {
        createEatingListViewModel.autoCompleteList.observe(viewLifecycleOwner) { list ->
            binding.autoCompleteDenomination.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    list
                )
            )
        }

        createEatingListViewModel.dishes.observe(viewLifecycleOwner) { dishes ->
            dishListAdapter.differ.submitList(dishes)
        }
    }

    private fun applyAdapters() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dishListAdapter
        }
    }

    private fun navigateToCalculateFragment() {
        if (createEatingListViewModel.dishListIsEmpty()) {
            view?.let { view ->
                Snackbar.make(view, getString(R.string.DISH_LIST_IS_EMPTY), Snackbar.LENGTH_LONG)
                    .show()
            }
        } else {
            findNavController().navigate(R.id.action_createEatingListFragment_to_totalCalculateFragment)
        }
    }

    private fun addDish() {
        val name = binding.autoCompleteDenomination.text.toString()
        val weight = binding.weightDecimal.text.toString()
        val quantity = binding.quantitySpinner.selectedItem.toString()

        if (checkFields(name, weight)) {
            createEatingListViewModel.addDish(
                name = name,
                weight = weight,
                quantity = quantity
            )
            clearFields()
        }
    }

    private fun clearFields() {
        binding.autoCompleteDenomination.text.clear()
        binding.weightDecimal.text.clear()
    }

    private fun checkFields(name: String, weight: String): Boolean {
        if (name.isEmpty()) {
            binding.autoCompleteDenomination.setNameIsEmpty()
            return false
        }
        if (createEatingListViewModel.isContains(name)) {
            binding.autoCompleteDenomination.setAllReadyExist()
            return false
        }
        if (createEatingListViewModel.checkDish(name)) {
            binding.autoCompleteDenomination.setDishNotFound()
            return false
        }
        if (weight.isEmpty()) {
            binding.weightDecimal.setFieldIsEmpty()
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}