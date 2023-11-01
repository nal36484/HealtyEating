package com.example.healthyeating.data.repositories.products

import com.example.healthyeating.data.mappers.ProductEntityMapper
import com.example.healthyeating.data.room.products.ProductBjuDao
import com.example.healthyeating.domain.entities.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductsLocalDataSourceImpl(
    private val dao: ProductBjuDao,
    private val mapper: ProductEntityMapper
) : ProductsLocalDataSource {
    override suspend fun getAllProducts(): Flow<List<Product>> {
        val productsFlow = dao.getProducts()
        return productsFlow.map { list ->
            list.map { element ->
                mapper.toProduct(element)
            }
        }
    }

    override suspend fun addAllProducts(products: List<Product>) {
        dao.saveProducts(products.map { element ->
            mapper.toProductEntity(element)
        })
    }

    override suspend fun addProduct(product: Product) {
        dao.saveProduct(mapper.toProductEntity(product))
    }

    override suspend fun deleteProduct(product: Product) {
        dao.deleteProduct(mapper.toProductEntity(product))
    }

    override suspend fun getProductByName(name: String): Flow<Product> {
        val productFlow = dao.getProductByName(name)
        return productFlow.map { entity ->
            mapper.toProduct(entity)
        }
    }
}