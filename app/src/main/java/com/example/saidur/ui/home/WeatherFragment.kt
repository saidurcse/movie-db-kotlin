package com.example.saidur.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.saidur.MainActivity
import com.example.saidur.databinding.FragmentWeatherBinding
import com.example.saidur.utils.*
import kotlinx.android.synthetic.main.fragment_weather.view.*
import kotlinx.android.synthetic.main.layout_sunrise_sunset.view.*
import kotlinx.android.synthetic.main.layout_weather_additional_info.view.*
import kotlinx.android.synthetic.main.layout_weather_basic_info.*
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
            val weatherConditionIconUrl = "http://openweathermap.org/img/w/${weatherData.weather?.get(0)!!.icon}.png"
            bindingView.layoutWeatherBasic.tv_date_time.text = weatherData.dt!!.unixTimestampToDateTimeString()
            bindingView.layoutWeatherBasic.tv_temperature.text = weatherData.main!!.temp!!.kelvinToCelsius().toString()
            bindingView.layoutWeatherBasic.tv_city_country.text = weatherData.name + weatherData.sys!!.country
            Glide.with(this).load(weatherConditionIconUrl).into(iv_weather_condition)
            bindingView.layoutWeatherBasic.tv_weather_condition.text = weatherData.weather.get(0)!!.description
            bindingView.layoutWeatherAdditional.tv_humidity_value.text = weatherData.main.humidity.toString()
            bindingView.layoutWeatherAdditional.tv_pressure_value.text = weatherData.main.pressure.toString()
            bindingView.layoutSunsetSunrise.tv_sunrise_time.text = weatherData.sys.sunrise!!.unixTimestampToTimeString()
            bindingView.layoutSunsetSunrise.tv_sunset_time.text = weatherData.sys.sunset!!.unixTimestampToTimeString()
        })


        return bindingView.root
    }

    override fun onClick(clickedView: View) {
        when (clickedView.id) {

        }
    }
}
