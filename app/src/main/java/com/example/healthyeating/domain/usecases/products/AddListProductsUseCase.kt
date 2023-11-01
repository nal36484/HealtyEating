package com.example.healthyeating.domain.usecases.products

import com.example.healthyeating.domain.entities.Product
import com.example.healthyeating.domain.repositories.ProductsRepository

class AddListProductsUseCase(private val repository: ProductsRepository) {
    suspend fun execute(products: List<Product>) {
        repository.addAllProducts(products)
    }
}