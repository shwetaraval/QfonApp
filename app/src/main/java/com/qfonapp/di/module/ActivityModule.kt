package com.qfonapp.di.module

import com.qfonapp.di.annotation.ActivityScope
import com.qfonapp.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Shweta 11/9/20
 */
@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}