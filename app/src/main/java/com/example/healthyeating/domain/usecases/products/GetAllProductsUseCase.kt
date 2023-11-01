package com.example.healthyeating.domain.usecases.products

import com.example.healthyeating.domain.entities.Product
import com.example.healthyeating.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(private val repository: ProductsRepository) {
    suspend fun execute(): Flow<List<Product>> {
        return repository.getAllProducts()
    }
}