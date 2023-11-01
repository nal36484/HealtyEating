package com.example.healthyeating.domain.usecases.dishes

import com.example.healthyeating.domain.entities.Dish
import com.example.healthyeating.domain.repositories.DishesRepository

class AddListDishesUseCase(private val repository: DishesRepository) {
    suspend fun execute(dishes: List<Dish>) {
        repository.addAllDishes(dishes)
    }
}