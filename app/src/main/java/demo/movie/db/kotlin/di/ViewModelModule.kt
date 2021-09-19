package demo.movie.db.kotlin.di

import demo.movie.db.kotlin.ui.home.HomeListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeListViewModel(repository = get())
    }

}