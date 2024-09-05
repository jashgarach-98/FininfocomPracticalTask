package com.example.finfocompracticaltask.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


inline fun <reified T : Activity> Context.launchActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(block))
}


fun Context.getColorCompat(colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

val backgroundScope = CoroutineScope(Dispatchers.IO)
val mainScope = CoroutineScope(Dispatchers.Main)

fun Context.toastMessage(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.getStringValue(string: Int): String {
    return resources.getString(string)
}

fun Activity.onBackButtonPressed(callback: (() -> Boolean)) {
    (this as? FragmentActivity)?.onBackPressedDispatcher?.addCallback(
        this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!callback()) {
                    remove()
                    performBackPress()
                }
            }
        })
}

fun Activity.performBackPress() {
    (this as? FragmentActivity)?.onBackPressedDispatcher?.onBackPressed()
}

fun findPrimeNumber(number: Int): Boolean {
    if (number <= 1) return false
    if (number == 2) return true
    if (number % 2 == 0) return false
    for (i in 3..Math.sqrt(number.toDouble()).toInt() step 2) {
        if (number % i == 0) return false
    }
    return true
}

fun findFibonacciNo(num: Int): Boolean {
    var a = 0
    var b = 1
    var c = 0
    while (c < num) {
        c = a + b
        a = b
        b = c
    }
    return c == num
}
