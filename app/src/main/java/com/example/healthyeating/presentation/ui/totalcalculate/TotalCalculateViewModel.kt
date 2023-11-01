package com.example.healthyeating.presentation.ui.totalcalculate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.healthyeating.domain.entities.Dish
import com.example.healthyeating.domain.entities.Product
import com.example.healthyeating.domain.usecases.dishes.GetAllDishesUseCase
import com.example.healthyeating.domain.usecases.products.GetProductByName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.atomic.AtomicInteger

class TotalCalculateViewModel(
    private val getAllDishesUseCase: GetAllDishesUseCase,
    private val getProductByName: GetProductByName,
    private val formatter: CaloriesFormatter
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _dishes = MutableLiveData<List<Dish>>()
    val dishes = _dishes

    private val _products = MutableLiveData<List<Product>>()
    val products = _products

    private val productList = mutableListOf<Product>()

    private val completedCount = AtomicInteger(0)

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllDishes()
        }
    }

    private fun getAllDishes() {
        viewModelScope.launch(Dispatchers.IO) {
            val dishesFlow = getAllDishesUseCase.execute()

            dishesFlow.map { dishList ->
                dishList.map { dish ->
                    val product = getProductByName.execute(dish.name).first()
                    val formattedProduct = formatter.format(dish, product)
                    productList.add(formattedProduct)
                    Timber.e(productList.toString() + "1")
                }
                Timber.e(productList.toString() + "2")
                val total = calculateTotal(productList)
                productList.add(total)
                products.postValue(productList)
            }.collect()
        }
    }

    private fun calculateTotal(productList: MutableList<Product>): Product {
        var squirrels = 0.0
        var fats = 0.0
        var carbohydrates = 0.0
        var kilocalories = 0.0
        for (product in productList) {
            squirrels += product.squirrels.toDouble()
            fats += product.fats.toDouble()
            carbohydrates += product.carbohydrates.toDouble()
            kilocalories += product.kilocalories.toDouble()
        }

        return Product(
            name = "Итого",
            squirrels = String.format("%.2f", squirrels),
            fats = String.format("%.2f", fats),
            carbohydrates = String.format("%.2f", carbohydrates),
            kilocalories = String.format("%.2f", kilocalories)
        )
    }


    class TotalCalculateViewModelFactory(
        private val getAllDishesUseCase: GetAllDishesUseCase,
        private val getProductByName: GetProductByName,
        private val formatter: CaloriesFormatter
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TotalCalculateViewModel(
                getAllDishesUseCase,
                getProductByName,
                formatter
            ) as T
        }
    }
}