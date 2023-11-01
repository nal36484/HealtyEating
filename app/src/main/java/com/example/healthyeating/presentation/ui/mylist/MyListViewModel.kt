package com.example.healthyeating.presentation.ui.mylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.healthyeating.domain.entities.Product
import com.example.healthyeating.domain.usecases.products.GetAllProductsUseCase
import kotlinx.coroutines.launch

class MyListViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _products = MutableLiveData<List<Product>>()
    val products = _products

    fun getAllProducts() {
        viewModelScope.launch {
            _dataLoading.postValue(true)

            val productsFlow = getAllProductsUseCase.execute()
            productsFlow.collect { list ->
                products.value = list
                _dataLoading.postValue(false)
            }
        }
    }

    class MyListViewModelFactory(
        private val getAllProductsUseCase: GetAllProductsUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MyListViewModel(
                getAllProductsUseCase
            ) as T
        }
    }
}