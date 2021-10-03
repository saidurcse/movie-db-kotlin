package demo.movie.db.kotlin.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import demo.movie.db.kotlin.data.api.model.RestListResponse
import demo.movie.db.kotlin.data.model.Movie
import demo.movie.db.kotlin.data.repository.MoviesRepository
import demo.movie.db.kotlin.getOrAwaitValue
import demo.movie.db.kotlin.utils.AppResult
import demo.movie.db.kotlin.utils.MainCoroutineRule
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
class HomeListViewModelTest {
    private val repository: MoviesRepository = mock(MoviesRepository::class.java)

    private lateinit var viewModel: HomeListViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    /// Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var offlineMovieListObserver: Observer<List<Movie>>

    @Mock
    lateinit var movieListObserver: Observer<List<Movie>>

    @Mock
    lateinit var showErrorObserver: Observer<String>

    @Before
    fun setUp() {
        // create view model
        viewModel = HomeListViewModel(repository)

        viewModel.offlineMovieList.observeForever(offlineMovieListObserver)
        viewModel.movieList.observeForever(movieListObserver)
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
        val dataResponse = MutableLiveData<List<Movie>>()
        dataResponse.value = mockMovieData()

        `when`(repository.getAllOfflineDB()).thenReturn(dataResponse)


        // when
        viewModel.getAllOfflineDB()

        // then
        val observeOfflineMovieListCapture = argumentCaptor<List<Movie>>()
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
                val dataResponse = MutableLiveData<List<Movie>>()
                dataResponse.value = mockMovieData()

                `when`(repository.getAllMovies()).thenReturn(AppResult.Success(getMockApiData()))

                // when
                viewModel.fetchMovieList()

                // then
                val observeMovieListCapture = argumentCaptor<List<Movie>>()
                viewModel.movieList.getOrAwaitValue()
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
                `when`(repository.getAllMovies()).thenReturn(AppResult.Error(Exception("Error!")))

                // when
                viewModel.fetchMovieList()

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
        Movie(title = "Test movie1"),
        Movie(title = "Test movie2"),
        Movie(title = "Test movie3")
    )
}