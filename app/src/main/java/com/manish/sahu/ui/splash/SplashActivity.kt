package com.manish.sahu.ui.splash

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.manish.sahu.databinding.ActivitySplashBinding
import com.manish.sahu.ui.home.MainActivity
import com.manish.sahu.ui.login.LoginActivity
import com.manish.sahu.utils.MySharedPreferences
import com.manish.sahu.utils.MySharedPreferences.Companion.IS_LOGIN
import com.manish.sahu.utils.startNewActivityClear

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarColor()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = MySharedPreferences(context = this)
        Handler(Looper.getMainLooper()).postDelayed({
            if(preferences.getMySharedPreferencesData(IS_LOGIN).equals("TRUE")){
                startNewActivityClear(MainActivity::class.java)}
            else{startNewActivityClear(LoginActivity::class.java)}

        }, 2000)
    }

    fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

}