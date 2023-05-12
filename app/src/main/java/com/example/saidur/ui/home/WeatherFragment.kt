package com.example.saidur.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.saidur.MainActivity
import com.example.saidur.databinding.FragmentWeatherBinding
import com.example.saidur.utils.SharedPreferencesHelper
import com.example.saidur.utils.SharedPreferencesKey
import com.example.saidur.utils.unixTimestampToDateTimeString
import kotlinx.android.synthetic.main.layout_weather_basic_info.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment(), View.OnClickListener {

    private val moviesViewModel by viewModel<WeatherViewModel>()
    private lateinit var bindingView: FragmentWeatherBinding
    private lateinit var sharedPreferences: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingView = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        bindingView.lifecycleOwner = this
        bindingView.viewModel = moviesViewModel
        (requireActivity() as MainActivity).supportActionBar!!.hide()
        sharedPreferences = SharedPreferencesHelper(requireContext())

        /*if (sharedPreferences.get(SharedPreferencesKey.FIRST_TIME, false)!!) {
            moviesViewModel.offlineMovieList.observe(viewLifecycleOwner, Observer {
                //adapter.submitList(it)
            })
        } else {
            moviesViewModel.fetchWeatherData()
            sharedPreferences.put(SharedPreferencesKey.FIRST_TIME, true)
        }*/

        moviesViewModel.fetchWeatherData()
        moviesViewModel.weatherData.observe(viewLifecycleOwner, Observer { weatherData ->
            bindingView.layoutWeatherBasic.tv_date_time.text = weatherData.dt!!.unixTimestampToDateTimeString()
        })


        return bindingView.root
    }

    override fun onClick(clickedView: View) {
        when (clickedView.id) {

        }
    }
}
