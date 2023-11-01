package com.example.healthyeating.domain.repositories

import com.example.healthyeating.domain.entities.Dish
import kotlinx.coroutines.flow.Flow

interface DishesRepository {

    suspend fun getAllDishes(): Flow<List<Dish>>

    suspend fun addAllDishes(dishes: List<Dish>)

    suspend fun addDish(dish: Dish)

    suspend fun deleteDish(dish: Dish)
}