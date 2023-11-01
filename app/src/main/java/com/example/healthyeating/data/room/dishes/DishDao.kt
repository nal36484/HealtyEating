package com.example.healthyeating.data.room.dishes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDish(dishEntity: DishEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDishes(dishes: List<DishEntity>)

    @Delete
    suspend fun deleteDish(dishEntity: DishEntity)

    @Query("SELECT * FROM dishes")
    fun getDishes(): Flow<List<DishEntity>>
}