package com.example.healthyeating.data.repositories.dishes

import com.example.healthyeating.domain.entities.Dish
import com.example.healthyeating.domain.repositories.DishesRepository
import kotlinx.coroutines.flow.Flow

class DishesRepositoryImpl(
    private val localDataSource: DishesLocalDataSource
) : DishesRepository {
    override suspend fun getAllDishes(): Flow<List<Dish>> {
        return localDataSource.getAllDishes()
    }

    override suspend fun addAllDishes(dishes: List<Dish>) {
        localDataSource.addAllDishes(dishes)
    }

    override suspend fun addDish(dish: Dish) {
        localDataSource.addDish(dish)
    }

    override suspend fun deleteDish(dish: Dish) {
        localDataSource.deleteDish(dish)
    }
}