package com.example.healthyeating.data.room.products

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.healthyeating.domain.entities.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductBjuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(productBjuEntity: ProductBjuEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(products: List<ProductBjuEntity>)

    @Delete
    suspend fun deleteProduct(productBjuEntity: ProductBjuEntity)

    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductBjuEntity>>

    @Query("SELECT * FROM products WHERE name = :name")
    fun getProductByName(name: String): Flow<ProductBjuEntity>
}