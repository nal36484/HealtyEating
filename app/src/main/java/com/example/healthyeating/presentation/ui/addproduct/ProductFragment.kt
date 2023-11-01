package com.example.healthyeating.presentation.ui.addproduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.healthyeating.R
import com.example.healthyeating.databinding.FragmentProductBinding
import com.example.healthyeating.presentation.ui.utils.checkDecimalOrSetError
import com.example.healthyeating.presentation.ui.utils.checkNameOrSetError
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val productViewModel: ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)

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
            val name = binding.productNameEdit.text.toString()
            val squirrels = binding.squirrelsDecimal.text.toString()
            val fats = binding.fatsDecimal.text.toString()
            val carbohydrates = binding.carbohydratesDecimal.text.toString()
            val kilocalories = binding.kilocaloriesDecimal.text.toString()

            if (checkName(name) && checkDecimal(squirrels, fats, carbohydrates, kilocalories)) {
                productViewModel.addProduct(
                    name = name,
                    squirrels = squirrels,
                    fats = fats,
                    carbohydrates = carbohydrates,
                    kilocalories = kilocalories

                )
                Toast.makeText(context, "Блюдо $name успешно добавлено", Toast.LENGTH_LONG)
                    .show()
                clearFields()
            }
        }
    }

    private fun clearFields() {
        binding.apply {
            productNameEdit.text.clear()
            squirrelsDecimal.text.clear()
            fatsDecimal.text.clear()
            carbohydratesDecimal.text.clear()
            kilocaloriesDecimal.text.clear()
        }
    }

    private fun checkDecimal(
        squirrels: String,
        fats: String,
        carbohydrates: String,
        kilocalories: String
    ): Boolean {
        return (binding.squirrelsDecimal.checkDecimalOrSetError(squirrels)
                && binding.fatsDecimal.checkDecimalOrSetError(fats)
                && binding.carbohydratesDecimal.checkDecimalOrSetError(carbohydrates)
                && binding.kilocaloriesDecimal.checkDecimalOrSetError(kilocalories))
    }

    private fun checkName(name: String): Boolean {
        return binding.productNameEdit.checkNameOrSetError(name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}