package com.qfonapp.base

/**
 * Created by Shweta
 */
open interface BaseNavigator {
    fun moveToBackPage() {}
    fun retryNetworkOperations() {}
    fun onRightButtonClicked() {}
}