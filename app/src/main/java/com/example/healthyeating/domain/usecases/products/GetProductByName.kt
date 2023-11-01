package com.example.healthyeating.domain.usecases.products

import com.example.healthyeating.domain.entities.Product
import com.example.healthyeating.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetProductByName(private val repository: ProductsRepository) {
    suspend fun execute(name: String): Flow<Product> {
        return repository.getProductByName(name)
    }
}