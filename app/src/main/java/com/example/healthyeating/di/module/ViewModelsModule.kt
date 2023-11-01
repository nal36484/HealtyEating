package com.example.healthyeating.di.module

import com.example.healthyeating.presentation.ui.mylist.MyListViewModel
import com.example.healthyeating.presentation.ui.addproduct.ProductViewModel
import com.example.healthyeating.presentation.ui.createeatinglist.CreateEatingListViewModel
import com.example.healthyeating.presentation.ui.mylist.MyListAdapter
import com.example.healthyeating.presentation.ui.totalcalculate.TotalCalculateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ProductViewModel(get()) }

    single { ProductViewModel.ProductViewModelFactory(get()) }

    viewModel { MyListViewModel(get()) }

    single { MyListViewModel.MyListViewModelFactory(get()) }

    viewModel { CreateEatingListViewModel(get(), get(), get(), get()) }

    single { CreateEatingListViewModel.CreateEatingListViewModelFactory(get(), get(), get(), get()) }

    viewModel { TotalCalculateViewModel(get(), get(), get()) }

    single { TotalCalculateViewModel.TotalCalculateViewModelFactory(get(), get(), get()) }
}