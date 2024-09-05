package com.example.finfocompracticaltask.presenter.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finfocompracticaltask.R
import com.example.finfocompracticaltask.data.model.AppRules
import com.example.finfocompracticaltask.databinding.ActivityHomeBinding
import com.example.finfocompracticaltask.presenter.adapter.NumberAdapter
import com.example.finfocompracticaltask.presenter.viewmodel.NumberViewModel
import com.example.finfocompracticaltask.utils.getStringValue
import com.example.finfocompracticaltask.utils.onBackButtonPressed
import com.example.finfocompracticaltask.utils.performBackPress
import com.example.finfocompracticaltask.utils.toastMessage

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding
    private var numberAdapter: NumberAdapter? = null
    private val viewModel: NumberViewModel by viewModels<NumberViewModel>()
    private var doubleBackToExitPressedOnce = false
    private val handler = Handler(Looper.getMainLooper())
    private val exitRunnable = Runnable {
        doubleBackToExitPressedOnce = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setAdapter()
        setNumbersDataList()

    }

    private fun setAdapter() {
        numberAdapter = NumberAdapter()
        binding.rvNumber.layoutManager = GridLayoutManager(this, 5)
        binding.rvNumber.adapter = numberAdapter
    }

    private fun init() {
        setSupportActionBar(binding.toolbar)
        onBackButtonPressed {
            if (doubleBackToExitPressedOnce) {
                performBackPress()
                finishAffinity()
            }
            this.doubleBackToExitPressedOnce = true
            toastMessage(getStringValue(R.string.double_tap_to_exit))
            // Reset the flag after 2 seconds
            handler.postDelayed(exitRunnable, 2000)
            true
        }
    }

    private fun setNumbersDataList() {
        viewModel.filteredNumbers.observe(this) { numbers ->
            numberAdapter?.submitList(numbers)
        }
        binding.appRuleSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val appRules = AppRules.entries[position]
                    viewModel.applyAppRules(appRules)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
    }
}