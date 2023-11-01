package com.example.healthyeating.data.room.products

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductBjuEntity::class], version = 1)
abstract class ProductsDataBase : RoomDatabase() {
    abstract fun productDao(): ProductBjuDao
}