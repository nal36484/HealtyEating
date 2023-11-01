package com.example.healthyeating.data.repositories.dishes

import com.example.healthyeating.domain.entities.Dish
import kotlinx.coroutines.flow.Flow

interface DishesLocalDataSource {

    suspend fun getAllDishes(): Flow<List<Dish>>

    suspend fun addAllDishes(dishes: List<Dish>)

    suspend fun addDish(dish: Dish)

    suspend fun deleteDish(dish: Dish)
}