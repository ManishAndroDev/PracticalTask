package com.manish.sahu.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.manish.sahu.R
import com.manish.sahu.databinding.ActivityDetailsBinding
import com.manish.sahu.databinding.ActivitySplashBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.text=intent.getStringExtra("title")
        Glide.with(this).load(intent.getStringExtra("url")).into(binding.imageview)
    }
}