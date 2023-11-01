package com.example.healthyeating.presentation.ui.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StartViewModel : ViewModel() {

    class StartViewModelFactory : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StartViewModel() as T
        }
    }
}