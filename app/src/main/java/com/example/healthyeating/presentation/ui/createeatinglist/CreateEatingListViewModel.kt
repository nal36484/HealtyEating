package com.example.healthyeating.presentation.ui.createeatinglist

import androidx.collection.arraySetOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.healthyeating.domain.entities.Dish
import com.example.healthyeating.domain.entities.Product
import com.example.healthyeating.domain.usecases.dishes.AddDishUseCase
import com.example.healthyeating.domain.usecases.dishes.DeleteDishUseCase
import com.example.healthyeating.domain.usecases.dishes.GetAllDishesUseCase
import com.example.healthyeating.domain.usecases.products.GetAllProductsUseCase
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CreateEatingListViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getAllDishesUseCase: GetAllDishesUseCase,
    private val addDishUseCase: AddDishUseCase,
    private val deleteDishUseCase: DeleteDishUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _products = MutableLiveData<List<Product>>()
    val products = _products

    private val _autoCompleteList = MutableLiveData<List<String>>()
    val autoCompleteList = _autoCompleteList

    private var _dishes = MutableLiveData<List<Dish>>()
    val dishes = _dishes

    private val dishList = ArrayList<Dish>()

    private val dishNames = arraySetOf<String>()

    fun dishListIsEmpty(): Boolean {
        return dishList.isEmpty()
    }

    fun checkDish(name: String): Boolean {
        products.value?.forEach { dish ->
            if (dish.name == name) {
                return false
            }
        }
        return true
    }

    fun addDish(name: String, weight: String, quantity: String) {
        viewModelScope.launch {
            val dish = Dish(
                name = name,
                weight = weight,
                quantity = quantity
            )
            addDishUseCase.execute(dish)
            dishNames.add(dish.name)
            dishList.add(dish)
            val tempList = ArrayList(dishList)
            _dishes.value = tempList
        }
    }

    fun deleteDish(dish: Dish) {
        viewModelScope.launch {
            deleteDishUseCase.execute(dish)
            dishNames.remove(dish.name)
            dishList.remove(dish)
            val tempList = ArrayList(dishList)
            _dishes.value = tempList
        }
    }

    fun isContains(name: String): Boolean {
        return dishNames.contains(name)
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                _dataLoading.postValue(true)
                getAllProducts()
                getAllDishes()
            } finally {
                _dataLoading.postValue(false)
            }
        }
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            val productsFlow = getAllProductsUseCase.execute()
            productsFlow.collect { list ->
                products.value = list
                autoCompleteList.value = createAutoCompleteList()
            }
        }
    }

    private fun getAllDishes() {
        viewModelScope.launch {
            val dishesFlow = getAllDishesUseCase.execute()
            dishesFlow.collect { list ->
                dishes.value = list
                dishList.addAll(list)
                list.forEach {dish ->
                    dishNames.add(dish.name)
                }
            }
        }
    }

    private fun createAutoCompleteList(): List<String> {
        val list: ArrayList<String> = arrayListOf()
        products.value?.forEach { element ->
            list.add(element.name)
        }

        return list
    }

    class CreateEatingListViewModelFactory(
        private val getAllProductsUseCase: GetAllProductsUseCase,
        private val getAllDishesUseCase: GetAllDishesUseCase,
        private val addDishUseCase: AddDishUseCase,
        private val deleteDishUseCase: DeleteDishUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CreateEatingListViewModel(
                getAllProductsUseCase,
                getAllDishesUseCase,
                addDishUseCase,
                deleteDishUseCase
            ) as T
        }
    }
}