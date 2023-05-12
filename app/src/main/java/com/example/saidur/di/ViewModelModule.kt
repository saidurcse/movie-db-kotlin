package com.example.saidur.di

import com.example.saidur.ui.home.HomeListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeListViewModel(repository = get())
    }

}