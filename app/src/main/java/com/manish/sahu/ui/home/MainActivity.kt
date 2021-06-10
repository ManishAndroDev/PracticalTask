package com.manish.sahu.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.manish.sahu.R
import com.manish.sahu.databinding.ActivityMainBinding
import com.manish.sahu.network.RetrofitService
import com.manish.sahu.repository.MainRepository
import com.manish.sahu.viewmodel.MyViewModelFactory
import com.manish.sahu.ui.adapters.PhotosAdapter
import com.manish.sahu.ui.login.LoginActivity
import com.manish.sahu.utils.MySharedPreferences
import com.manish.sahu.utils.startNewActivityClear
import com.manish.sahu.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()

    val adapter = PhotosAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.photoList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setPhotoList(it)
        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllPhotos()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                val preferences = MySharedPreferences(context = this)
                preferences.clearMyPref();
                startNewActivityClear(LoginActivity::class.java)
                Toast.makeText(applicationContext, "user logged out", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}