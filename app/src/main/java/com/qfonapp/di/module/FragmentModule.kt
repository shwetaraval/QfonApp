package com.qfonapp.di.module

import com.qfonapp.di.annotation.FragmentScope
import com.qfonapp.ui.fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Shweta 11/9/20
 */
@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment
}