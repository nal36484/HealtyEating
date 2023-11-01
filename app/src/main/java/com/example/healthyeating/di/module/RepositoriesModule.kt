package com.example.healthyeating.di.module

import androidx.room.Room
import com.example.healthyeating.data.mappers.DishEntityMapper
import com.example.healthyeating.data.mappers.ProductEntityMapper
import com.example.healthyeating.data.repositories.dishes.DishesLocalDataSource
import com.example.healthyeating.data.repositories.dishes.DishesLocalDataSourceImpl
import com.example.healthyeating.data.repositories.dishes.DishesRepositoryImpl
import com.example.healthyeating.data.repositories.products.ProductsLocalDataSource
import com.example.healthyeating.data.repositories.products.ProductsLocalDataSourceImpl
import com.example.healthyeating.data.repositories.products.ProductsRepositoryImpl
import com.example.healthyeating.data.room.dishes.DishesDataBase
import com.example.healthyeating.data.room.products.ProductsDataBase
import com.example.healthyeating.domain.repositories.DishesRepository
import com.example.healthyeating.domain.repositories.ProductsRepository
import com.example.healthyeating.presentation.ui.totalcalculate.CaloriesFormatter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoriesModule = module {
    single<ProductsRepository> { ProductsRepositoryImpl(get()) }

    single<ProductsLocalDataSource> { ProductsLocalDataSourceImpl(get(), get()) }

    single {
        Room.databaseBuilder(androidContext(), ProductsDataBase::class.java, "productsDatabase")
        .build()
    }

    single { get<ProductsDataBase>().productDao() }

    factory { ProductEntityMapper() }

    single<DishesRepository> { DishesRepositoryImpl(get()) }

    single<DishesLocalDataSource> { DishesLocalDataSourceImpl(get(), get()) }

    single {
        Room.databaseBuilder(androidContext(), DishesDataBase::class.java, "dishesDatabase")
            .build()
    }

    single { get<DishesDataBase>().dishDao() }

    factory { DishEntityMapper() }

    factory { CaloriesFormatter() }
}