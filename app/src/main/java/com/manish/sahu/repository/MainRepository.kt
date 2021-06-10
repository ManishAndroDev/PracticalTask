package com.manish.sahu.repository

import com.manish.sahu.network.RetrofitService


/**
 * Created by Manish Sahu on 09-Jun-21.
 * Email: sahum652@gmail.com
 */

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllData() = retrofitService.getData()
}