package com.qfonapp.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.qfonapp.utils.Logger
import java.lang.ref.WeakReference

/**
 * Created by Shweta
 */
abstract class BaseViewModel<N : BaseNavigator>(application: Application) :
    AndroidViewModel(application) {
    private var mNavigator: WeakReference<N>? = null

    fun getNavigator(): N? {
        return mNavigator!!.get()
    }

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

    public fun showLog() {
        Logger.e(this.javaClass.name + ", " + "INITIALIZED")
    }

    fun moveToBackPage() {
        Logger.e(this.javaClass.name + ", " + "CLICKED")
        if (getNavigator() is BaseNavigator) {
            getNavigator()?.moveToBackPage()
        }
    }

    fun retryNetworkOperations() {
        Logger.e(this.javaClass.name + ", " + "CLICKED")
        if (getNavigator() is BaseNavigator) {
            getNavigator()?.retryNetworkOperations()
        }
    }

    fun rightButtonClicked() {
        Logger.e(this.javaClass.name + ", " + "CLICKED")
        if (getNavigator() is BaseNavigator) {
            getNavigator()?.onRightButtonClicked()
        }
    }

}