package com.wipro.assignment.mvvm.java
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wipro.assignment.mvvm.about.model.AboutApiResponse
import com.wipro.assignment.mvvm.about.model.AboutList
import com.wipro.assignment.mvvm.network.api.ApiClient
import com.wipro.assignment.mvvm.about.viewmodel.AboutViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit
/**
 * Here we can tests the view model negative and positive cases but in this apis only provide static
 * link, if provide some argument then we can test that. So data will come in each case here
 * if internet available.
 */
@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class AboutViewModelApiTest{
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    @Mock
    lateinit var retrofit: Retrofit
    @Mock
    lateinit var apiClient: ApiClient
    lateinit var aboutViewModel: AboutViewModel
    private lateinit var response: Response<AboutApiResponse>
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }
    val testDispatcher = TestCoroutineDispatcher()
    @Test
    fun `check list aboutviewmodel to fetch  data correctly`() = testDispatcher.runBlockingTest{
        var data=AboutList("Beavers","Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony","https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg")
        var title="About Canada"
        var list = arrayListOf<AboutList>()
        list.add(data)
        var dummyData=AboutApiResponse(title,list)
        response = Response.success(dummyData)
        aboutViewModel = AboutViewModel(testDispatcher,apiClient)
        if (retrofit != null) {

            if (apiClient != null) {
                Mockito.`when`(apiClient.getData()).thenReturn(response)
            }
        }
        aboutViewModel.getDataValue()
        println("Loading Val::::${aboutViewModel.fetchLoadStatus()?.value}")
        println("PostLive Data::::${aboutViewModel.fetchAboutLiveData().value}")
        Assert.assertEquals(1,aboutViewModel.fetchAboutLiveData().value?.size)
        Assert.assertEquals(false, aboutViewModel.fetchLoadStatus()?.value)
    }
}