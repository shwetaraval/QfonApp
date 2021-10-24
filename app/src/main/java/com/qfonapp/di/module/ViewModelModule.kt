package com.qfonapp.di.module

import androidx.lifecycle.ViewModel
import com.qfonapp.di.annotation.ViewModelKey
import com.qfonapp.viewModels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeActivityViewModels(homeActivityViewModel: HomeViewModel): ViewModel
}
