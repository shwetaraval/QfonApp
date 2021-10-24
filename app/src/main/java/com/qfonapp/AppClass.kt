package com.qfonapp

import com.qfonapp.di.component.DaggerAppComponent
import dagger.android.DaggerApplication

/**
 * Created by Shweta 14/9/20
 */
open class AppClass : DaggerApplication() {
    private val appComponent = DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        //initialize
    }

    override fun applicationInjector() = appComponent
}