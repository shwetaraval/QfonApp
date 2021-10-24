package com.qfonapp.di.component

import android.app.Application
import com.qfonapp.di.module.ActivityModule
import com.qfonapp.di.module.FragmentModule
import com.qfonapp.di.module.NetworkModule
import com.qfonapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

/**
 * Created by Shweta 11/9/20
 */

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityModule::class,FragmentModule::class, ViewModelModule::class, NetworkModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}