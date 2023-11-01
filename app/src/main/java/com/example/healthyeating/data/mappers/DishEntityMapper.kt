package com.example.healthyeating.data.mappers

import com.example.healthyeating.data.room.dishes.DishEntity
import com.example.healthyeating.domain.entities.Dish

class DishEntityMapper {
    fun toDishEntity(dish: Dish): DishEntity {
        return DishEntity(
            name = dish.name,
            weight = dish.weight,
            quantity = dish.quantity
        )
    }

    fun toDish(dishEntity: DishEntity): Dish {
        return Dish(
            name = dishEntity.name,
            weight = dishEntity.weight,
            quantity = dishEntity.quantity
        )
    }
}