package com.example.saidur.ui.home

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.saidur.MainActivity
import com.example.saidur.R
import com.example.saidur.databinding.FragmentWeatherBinding
import com.example.saidur.utils.*
import kotlinx.android.synthetic.main.layout_input_part.view.*
import kotlinx.android.synthetic.main.layout_sunrise_sunset.view.*
import kotlinx.android.synthetic.main.layout_weather_additional_info.view.*
import kotlinx.android.synthetic.main.layout_weather_basic_info.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment(), View.OnClickListener {

    private val weatherViewModel by viewModel<WeatherViewModel>()
    private lateinit var bindingView: FragmentWeatherBinding
    private lateinit var sharedPreferences: SharedPreferencesHelper
    val REQUEST_ACCESS_FINE_LOCATION = 22

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingView = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        bindingView.lifecycleOwner = this
        bindingView.viewModel = weatherViewModel
        (requireActivity() as MainActivity).supportActionBar!!.hide()
        sharedPreferences = SharedPreferencesHelper(requireContext())

        /*if (sharedPreferences.get(SharedPreferencesKey.FIRST_TIME, false)!!) {
            weathersViewModel.offlineMovieList.observe(viewLifecycleOwner, Observer {
                //adapter.submitList(it)
            })
        } else {
            weathersViewModel.fetchWeatherData()
            sharedPreferences.put(SharedPreferencesKey.FIRST_TIME, true)
        }*/

        weatherViewModel.weatherLatLongData.observe(viewLifecycleOwner, Observer { weatherLatLongData ->
            val lat = weatherLatLongData.get(0).lat.toString()
            val lon =  weatherLatLongData.get(0).lon.toString()
            weatherViewModel.fetchWeatherData(lat,lon)
        })

        weatherViewModel.weatherData.observe(viewLifecycleOwner, Observer { weatherData ->
            val weatherConditionIconUrl = "https://openweathermap.org/img/w/${weatherData.weather?.get(0)!!.icon}.png"
            bindingView.layoutWeatherBasic.tv_date_time.text = weatherData.dt!!.unixTimestampToDateTimeString()
            bindingView.layoutWeatherBasic.tv_temperature.text = weatherData.main!!.temp!!.kelvinToCelsius().toString()
            bindingView.layoutWeatherBasic.tv_city_country.text = weatherData.name + weatherData.sys!!.country

            Glide.with(bindingView.layoutWeatherBasic.iv_weather_condition).
            load(weatherConditionIconUrl).
            into(bindingView.layoutWeatherBasic.iv_weather_condition)

            bindingView.layoutWeatherBasic.tv_weather_condition.text = weatherData.weather.get(0)!!.description
            bindingView.layoutWeatherAdditional.tv_humidity_value.text = weatherData.main.humidity.toString()
            bindingView.layoutWeatherAdditional.tv_pressure_value.text = weatherData.main.pressure.toString()
            bindingView.layoutSunsetSunrise.tv_sunrise_time.text = weatherData.sys.sunrise!!.unixTimestampToTimeString()
            bindingView.layoutSunsetSunrise.tv_sunset_time.text = weatherData.sys.sunset!!.unixTimestampToTimeString()
        })


        bindingView.layoutInput.btn_view_weather.setOnClickListener(this)
        return bindingView.root
    }

    override fun onClick(clickedView: View) {
        when (clickedView.id) {

            R.id.btn_view_weather -> {
                PermissionUtil.checkPermission(context as Activity, Manifest.permission.ACCESS_FINE_LOCATION,
                    object : PermissionUtil.PermissionAskListener {
                        override fun onNeedPermission() {
                            ActivityCompat.requestPermissions(
                                (context as Activity),
                                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                REQUEST_ACCESS_FINE_LOCATION
                            )
                        }

                        override fun onPermissionPreviouslyDenied() {
                            //show a dialog explaining permission and then request permission
                            AlertDialog.Builder(context as Activity)
                                .setTitle(getString(R.string.location_permission_needed))
                                .setMessage(getString(R.string.location_permission_accept))
                                .setPositiveButton(getString(R.string.ok)) { dialogInterface: DialogInterface?, i: Int ->
                                    ActivityCompat.requestPermissions(
                                        (context as Activity),
                                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                        REQUEST_ACCESS_FINE_LOCATION
                                    )
                                }
                                .create()
                                .show()
                        }

                        override fun onPermissionDisabled() {
                            Toast.makeText(context as Activity, getString(R.string.permission_disabled), Toast.LENGTH_SHORT).show()
                        }

                        override fun onPermissionGranted() {
                            val cityName = bindingView.layoutInput.input_city_name.text.toString()
                            if(cityName.isNotEmpty()) {
                                weatherViewModel.fetchLatLonData("http://api.openweathermap.org/geo/1.0/direct" , cityName)
                            }
                        }
                    })
            }
        }
    }
}
