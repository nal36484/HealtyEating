package com.example.healthyeating.data.room.dishes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")
data class DishEntity(
    @PrimaryKey
    val name: String,
    val weight: String,
    val quantity: String
)
