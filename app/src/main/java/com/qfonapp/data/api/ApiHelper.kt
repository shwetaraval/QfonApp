package com.qfonapp.data.api

import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getPostList() = apiService.getPostList()
}