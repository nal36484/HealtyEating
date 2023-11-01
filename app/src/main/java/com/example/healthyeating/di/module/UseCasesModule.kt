package com.example.healthyeating.di.module

import com.example.healthyeating.domain.usecases.dishes.AddDishUseCase
import com.example.healthyeating.domain.usecases.dishes.AddListDishesUseCase
import com.example.healthyeating.domain.usecases.dishes.DeleteDishUseCase
import com.example.healthyeating.domain.usecases.dishes.GetAllDishesUseCase
import com.example.healthyeating.domain.usecases.products.AddListProductsUseCase
import com.example.healthyeating.domain.usecases.products.AddProductUseCase
import com.example.healthyeating.domain.usecases.products.DeleteProductUseCase
import com.example.healthyeating.domain.usecases.products.GetAllProductsUseCase
import com.example.healthyeating.domain.usecases.products.GetProductByName
import org.koin.dsl.module

val useCasesModule = module {
    single { AddProductUseCase(get()) }

    single { GetAllProductsUseCase(get()) }

    single { DeleteProductUseCase(get()) }

    single { AddListProductsUseCase(get()) }

    single { GetProductByName(get()) }

    single { AddDishUseCase(get()) }

    single { AddListDishesUseCase(get()) }

    single { DeleteDishUseCase(get()) }

    single { GetAllDishesUseCase(get()) }
}