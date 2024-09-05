package com.example.finfocompracticaltask.presenter.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finfocompracticaltask.databinding.ActivitySplashBinding
import com.example.finfocompracticaltask.utils.launchActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.btnGetStart.setOnClickListener {
            launchActivity<HomeActivity>()
        }
    }
}