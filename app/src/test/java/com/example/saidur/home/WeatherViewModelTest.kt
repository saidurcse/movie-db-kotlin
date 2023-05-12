package com.example.saidur.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.example.saidur.data.api.model.RestListResponse
import com.example.saidur.data.model.WeatherInfoResponse
import com.example.saidur.data.repository.WeatherRepository
import com.example.saidur.ui.home.WeatherViewModel
import com.example.saidur.getOrAwaitValue
import com.example.saidur.utils.AppResult
import com.example.saidur.utils.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {
    private val repository: WeatherRepository = mock(WeatherRepository::class.java)

    private lateinit var viewModel: WeatherViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    /// Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var offlineMovieListObserver: Observer<WeatherInfoResponse>

    @Mock
    lateinit var movieListObserver: Observer<WeatherInfoResponse>

    @Mock
    lateinit var showErrorObserver: Observer<String>

    @Before
    fun setUp() {
        // create view model
        viewModel = WeatherViewModel(repository)

        viewModel.offlineMovieList.observeForever(offlineMovieListObserver)
        viewModel.weatherData.observeForever(movieListObserver)
        viewModel.showError.observeForever(showErrorObserver)
    }

    @After
    fun tearDown() {
        reset(offlineMovieListObserver)
        reset(movieListObserver)
        reset(showErrorObserver)
    }

    @Test
    fun `should get data from DB`() {
        // given
        val dataResponse = MutableLiveData<WeatherInfoResponse>()
        dataResponse.value = mockMovieData()

        `when`(repository.getAllOfflineDB()).thenReturn(dataResponse)

        // when
        viewModel.getAllOfflineDB()

        // then
        val observeOfflineMovieListCapture = argumentCaptor<WeatherInfoResponse>()
        viewModel.offlineMovieList.getOrAwaitValue()
        verify(offlineMovieListObserver).onChanged(observeOfflineMovieListCapture.capture())

        assertEquals(
            mockMovieData(),
            observeOfflineMovieListCapture.firstValue
        )
    }

    @Test
    fun `should fetch data from api successfully`() {
        runBlocking {
            launch(Dispatchers.Main) {
                // given
                val dataResponse = MutableLiveData<WeatherInfoResponse>()
                dataResponse.value = mockMovieData()

                `when`(repository.getWeatherDataInfo()).thenReturn(AppResult.Success(getMockApiData()))

                // when
                viewModel.fetchWeatherData("32.8140", "96.9489")

                // then
                val observeMovieListCapture = argumentCaptor<WeatherInfoResponse>()
                viewModel.weatherData.getOrAwaitValue()
                verify(movieListObserver).onChanged(observeMovieListCapture.capture())

                assertEquals(
                    mockMovieData(),
                    observeMovieListCapture.firstValue
                )

                val observeErrorCapture = argumentCaptor<String>()
                viewModel.showError.getOrAwaitValue()
                verify(showErrorObserver).onChanged(observeErrorCapture.capture())


                assertNull(observeErrorCapture.firstValue)
            }
        }
    }

    @Test
    fun `should show error when fetching movie from api`() {
        runBlocking {
            launch(Dispatchers.Main) {
                // given
                `when`(repository.getWeatherDataInfo()).thenReturn(AppResult.Error(Exception("Error!")))

                // when
                viewModel.fetchWeatherData("32.8140", "96.9489")

                // then
                val observeErrorCapture = argumentCaptor<String>()
                viewModel.showError.getOrAwaitValue()
                verify(showErrorObserver).onChanged(observeErrorCapture.capture())


                assertNotNull(observeErrorCapture.firstValue)
            }
        }
    }

    private fun getMockApiData() = RestListResponse(
        results = mockMovieData().toMutableList(),
        page = 0,
        total_pages = 0,
        total_results = 0
    )

    private fun mockMovieData() = listOf(
        Weather(title = "Test movie1"),
        Weather(title = "Test movie2"),
        Weather(title = "Test movie3")
    )
}