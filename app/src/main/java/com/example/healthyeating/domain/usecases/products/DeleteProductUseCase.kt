package com.example.healthyeating.domain.usecases.products

import com.example.healthyeating.domain.entities.Product
import com.example.healthyeating.domain.repositories.ProductsRepository

class DeleteProductUseCase(private val repository: ProductsRepository) {
    suspend fun execute(product: Product) {
        repository.deleteProduct(product)
    }
}