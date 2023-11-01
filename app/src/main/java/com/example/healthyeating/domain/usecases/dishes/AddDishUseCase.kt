package com.example.healthyeating.domain.usecases.dishes

import com.example.healthyeating.domain.entities.Dish
import com.example.healthyeating.domain.repositories.DishesRepository

class AddDishUseCase(private val repository: DishesRepository) {
    suspend fun execute(dish: Dish) {
        repository.addDish(dish)
    }
}