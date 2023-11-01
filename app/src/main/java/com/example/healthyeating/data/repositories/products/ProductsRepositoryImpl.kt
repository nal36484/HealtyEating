package com.example.healthyeating.data.repositories.products

import com.example.healthyeating.domain.entities.Product
import com.example.healthyeating.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow

class ProductsRepositoryImpl(
    private val localDataSource: ProductsLocalDataSource
): ProductsRepository {
    override suspend fun getAllProducts(): Flow<List<Product>> {
        return localDataSource.getAllProducts()
    }

    override suspend fun addAllProducts(products: List<Product>) {
        localDataSource.addAllProducts(products)
    }

    override suspend fun addProduct(product: Product) {
        localDataSource.addProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        localDataSource.deleteProduct(product)
    }

    override suspend fun getProductByName(name: String): Flow<Product> {
        return localDataSource.getProductByName(name)
    }
}