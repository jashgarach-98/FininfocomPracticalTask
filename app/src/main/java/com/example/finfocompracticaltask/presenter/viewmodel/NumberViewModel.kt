package com.example.finfocompracticaltask.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finfocompracticaltask.data.model.AppRules
import com.example.finfocompracticaltask.data.model.NumberItem
import com.example.finfocompracticaltask.utils.backgroundScope
import com.example.finfocompracticaltask.utils.findFibonacciNo
import com.example.finfocompracticaltask.utils.findPrimeNumber
import com.example.finfocompracticaltask.utils.mainScope
import kotlinx.coroutines.launch

class NumberViewModel : ViewModel() {

    private val numbers = (1..100).toList()
    private val _filteredNumbers = MutableLiveData<List<NumberItem>>()
    val filteredNumbers: LiveData<List<NumberItem>> get() = _filteredNumbers

    init {
        applyAppRules(AppRules.ODD_NUMBER)
    }

    fun applyAppRules(appRules: AppRules) {
        backgroundScope.launch {
            val filtered = numbers.map { number ->
                NumberItem(number, highlightNumber(number = number, appRules = appRules), appRules = appRules)
            }
            mainScope.launch {
                _filteredNumbers.value = filtered
            }
        }
    }

    private fun highlightNumber(number: Int, appRules: AppRules): Boolean {
        return when (appRules) {
            AppRules.ODD_NUMBER -> number % 2 != 0
            AppRules.EVEN_NUMBER -> number % 2 == 0
            AppRules.PRIME_NUMBER -> findPrimeNumber(number)
            AppRules.FIBONACCI_SEQUENCE -> findFibonacciNo(number)
        }
    }
}

