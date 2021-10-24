package com.qfonapp.data

import com.qfonapp.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getPostList() = apiHelper.getPostList()
}