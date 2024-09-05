package com.example.finfocompracticaltask.data.model


data class NumberItem(val number: Int, val isSelected: Boolean, val appRules: AppRules)

enum class AppRules {
    ODD_NUMBER, EVEN_NUMBER, PRIME_NUMBER, FIBONACCI_SEQUENCE
}