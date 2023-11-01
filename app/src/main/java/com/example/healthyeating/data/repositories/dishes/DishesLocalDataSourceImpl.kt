package com.example.healthyeating.data.repositories.dishes

import com.example.healthyeating.data.mappers.DishEntityMapper
import com.example.healthyeating.data.room.dishes.DishDao
import com.example.healthyeating.domain.entities.Dish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DishesLocalDataSourceImpl(
    private val dao: DishDao,
    private val mapper: DishEntityMapper
) : DishesLocalDataSource {
    override suspend fun getAllDishes(): Flow<List<Dish>> {
        val dishesFlow = dao.getDishes()
        return dishesFlow.map { list ->
            list.map { element ->
                mapper.toDish(element)
            }
        }
    }

    override suspend fun addAllDishes(dishes: List<Dish>) {
        dao.saveDishes(dishes.map { element ->
            mapper.toDishEntity(element)
        })
    }

    override suspend fun addDish(dish: Dish) {
        dao.saveDish(mapper.toDishEntity(dish))
    }

    override suspend fun deleteDish(dish: Dish) {
        dao.deleteDish(mapper.toDishEntity(dish))
    }
}