package com.qfonapp.data.api


import com.qfonapp.data.model.PostModel
import com.qfonapp.data.api.Api
import com.qfonapp.data.model.PostResponseModel
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET(Api.POST_LIST)
    suspend fun getPostList(): PostResponseModel
}