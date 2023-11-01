package com.example.healthyeating.presentation.ui.utils

import android.widget.TextView
import com.example.healthyeating.R

fun TextView.setFieldIsEmpty() {
    error = context.getString(R.string.EMPTY_FIELD_ERROR)
}

fun TextView.checkDecimalOrSetError(text: String): Boolean {
    if (text.isEmpty()) {
        setFieldIsEmpty()
        return false
    }
    return true
}

fun TextView.setNameIsEmpty() {
    error = context.getString(R.string.EMPTY_NAME_ERROR)
}

fun TextView.checkNameOrSetError(text: String): Boolean {
    if (text.isEmpty()) {
        setNameIsEmpty()
        return false
    }
    return true
}

fun TextView.setAllReadyExist() {
    error = context.getString(R.string.ALL_READY_EXIST)
}

fun TextView.setDishNotFound() {
    error = context.getString(R.string.DISH_NOT_FOUND)
}