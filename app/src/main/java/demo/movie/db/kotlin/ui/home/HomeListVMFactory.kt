package demo.movie.db.kotlin.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import demo.movie.db.kotlin.utils.SharedPreferencesHelper

class HomeListVMFactory(
    private val repo: HomeListRepo,
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val context: Context
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeListViewModel(repo, sharedPreferencesHelper, context) as T
    }
}
