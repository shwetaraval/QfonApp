package com.qfonapp.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.qfonapp.base.BaseNavigator
import com.qfonapp.base.BaseViewModel
import com.qfonapp.data.MainRepository
import com.qfonapp.data.model.PostModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    application: Application,
    val mainRepository: MainRepository
) :
    BaseViewModel<BaseNavigator>(application) {
    //For api call

    private val _postList = MutableLiveData<ArrayList<PostModel>>()
    val postList: LiveData<ArrayList<PostModel>> = _postList

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    //Start api
    public fun getPostList() {
        _isViewLoading.value = true
        try {
            _isViewLoading.value = true
            CoroutineScope(Dispatchers.IO).launch {
                _postList?.postValue(mainRepository.getPostList().data)
            }
        } catch (e: HttpException) {
            _isEmptyList.value = true
        }
    }
    //ENd api
}