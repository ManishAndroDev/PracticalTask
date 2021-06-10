package com.manish.sahu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manish.sahu.model.Photos
import com.manish.sahu.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Manish Sahu on 09-Jun-21.
 * Email: sahum652@gmail.com
 */

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val photoList = MutableLiveData<List<Photos>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllPhotos() {

        val response = repository.getAllData()
        response.enqueue(object : Callback<List<Photos>> {
            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {
                photoList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
