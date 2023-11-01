package com.example.healthyeating.data.repositories.products

import com.example.healthyeating.domain.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductsLocalDataSource {

    suspend fun getAllProducts(): Flow<List<Product>>

    suspend fun addAllProducts(products: List<Product>)

    suspend fun addProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun getProductByName(name: String): Flow<Product>
}