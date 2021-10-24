package com.qfonapp.data.model

import com.qfonapp.data.model.BaseModel

data class PostResponseModel(
    override var status: Boolean,
    override var message: String,
    val data: ArrayList<PostModel>
) : BaseModel
