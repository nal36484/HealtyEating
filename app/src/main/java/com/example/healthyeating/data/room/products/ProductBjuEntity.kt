package com.example.healthyeating.data.room.products

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductBjuEntity(
    @PrimaryKey
    val name: String,
    val squirrels: String,
    val fats: String,
    val carbohydrates: String,
    val kilocalories: String
)
