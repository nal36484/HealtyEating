package com.example.healthyeating.domain.repositories

import com.example.healthyeating.domain.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getAllProducts(): Flow<List<Product>>

    suspend fun addAllProducts(products: List<Product>)

    suspend fun addProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun getProductByName(name: String): Flow<Product>
}