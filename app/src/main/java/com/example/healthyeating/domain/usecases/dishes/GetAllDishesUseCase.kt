package com.example.healthyeating.domain.usecases.dishes

import com.example.healthyeating.domain.entities.Dish
import com.example.healthyeating.domain.repositories.DishesRepository
import kotlinx.coroutines.flow.Flow

class GetAllDishesUseCase(private val repository: DishesRepository) {
    suspend fun execute(): Flow<List<Dish>> {
        return repository.getAllDishes()
    }
}