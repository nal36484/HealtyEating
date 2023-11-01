package com.example.healthyeating.data.room.dishes

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DishEntity::class], version = 1)
abstract class DishesDataBase : RoomDatabase() {
    abstract fun dishDao(): DishDao
}