package com.example.healthyeating.presentation.ui.totalcalculate

import com.example.healthyeating.domain.entities.Dish
import com.example.healthyeating.domain.entities.Product
import java.text.DecimalFormat

class CaloriesFormatter {
    fun format(dish: Dish, product: Product): Product {
        val weight = dish.weight.toDouble() / 100
        val quantity = dish.quantity.toDouble()
        val df = DecimalFormat("#.##")

        val squirrels = df.format(product.squirrels.toDouble() * weight * quantity).toString()
        val fats = df.format(product.fats.toDouble() * weight * quantity).toString()
        val carbohydrates = df.format(product.carbohydrates.toDouble() * weight * quantity).toString()
        val kilocalories = df.format(product.kilocalories.toDouble() * weight * quantity).toString()

        return Product(
            name = product.name,
            squirrels = squirrels,
            fats = fats,
            carbohydrates = carbohydrates,
            kilocalories = kilocalories
        )
    }
}