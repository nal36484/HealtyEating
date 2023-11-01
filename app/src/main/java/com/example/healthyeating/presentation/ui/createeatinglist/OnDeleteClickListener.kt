package com.example.healthyeating.presentation.ui.createeatinglist

import com.example.healthyeating.domain.entities.Dish

interface OnDeleteClickListener {
    fun deleteDish(dish: Dish)
}