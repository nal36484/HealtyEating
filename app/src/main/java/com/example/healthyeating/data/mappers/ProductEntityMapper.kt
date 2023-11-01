package com.example.healthyeating.data.mappers

import com.example.healthyeating.data.room.products.ProductBjuEntity
import com.example.healthyeating.domain.entities.Product

class ProductEntityMapper {
    fun toProductEntity(product: Product): ProductBjuEntity {
        return ProductBjuEntity(
            name = product.name,
            squirrels = product.squirrels,
            fats = product.fats,
            carbohydrates = product.carbohydrates,
            kilocalories = product.kilocalories
        )
    }

    fun toProduct(productBjuEntity: ProductBjuEntity): Product {
        return Product(
            name = productBjuEntity.name,
            squirrels = productBjuEntity.squirrels,
            fats = productBjuEntity.fats,
            carbohydrates = productBjuEntity.carbohydrates,
            kilocalories = productBjuEntity.kilocalories
        )
    }
}