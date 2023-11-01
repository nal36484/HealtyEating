package com.example.healthyeating.presentation.ui.addproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.healthyeating.domain.entities.Product
import com.example.healthyeating.domain.usecases.products.AddProductUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    fun addProduct(
        name: String,
        squirrels: String,
        fats: String,
        carbohydrates: String,
        kilocalories: String
    ) {
        viewModelScope.launch { addProductUseCase.execute(
            Product(
                name = name,
                squirrels = squirrels,
                fats = fats,
                carbohydrates = carbohydrates,
                kilocalories = kilocalories
            )
        ) }
    }

    class ProductViewModelFactory(
        private val addProductUseCase: AddProductUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductViewModel(
                addProductUseCase
            ) as T
        }
    }
}