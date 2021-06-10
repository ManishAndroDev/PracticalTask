package com.manish.sahu.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.manish.sahu.R
import com.manish.sahu.databinding.ActivityLoginBinding
import com.manish.sahu.databinding.ActivitySplashBinding
import com.manish.sahu.ui.home.MainActivity
import com.manish.sahu.utils.MySharedPreferences
import com.manish.sahu.utils.MySharedPreferences.Companion.IS_LOGIN
import com.manish.sahu.utils.snackbar
import com.manish.sahu.utils.startNewActivityClear

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferences = MySharedPreferences(context = this)

       binding.buttonSignIn.setOnClickListener {
           if (validate()) {
               preferences.setMySharedPreferencesData(IS_LOGIN, "TRUE")
               startNewActivityClear(MainActivity::class.java)
           }
       }


    }

    private fun validate():Boolean{

        if(TextUtils.isEmpty(binding.editTextEmail.text.toString())){
            binding.editTextEmail.error = "Enter Email"
            binding.editTextEmail.requestFocus()
            return  false
        }else if(TextUtils.isEmpty(binding.editTextPassword.text.toString())){
            binding.editTextPassword.error = "Enter Password"
            binding.editTextPassword.requestFocus()
            return  false
        }else if (binding.editTextEmail.text.toString() == "hello@yopmail.com" && binding.editTextPassword.text.toString()=="Password@123")
        {
            return  true
        }else {
            binding.root.snackbar("Invalid User ID or Password")
            return false
        }
    }
}