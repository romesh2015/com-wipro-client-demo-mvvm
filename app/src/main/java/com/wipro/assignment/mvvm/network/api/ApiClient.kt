package com.wipro.assignment.mvvm.network.api
import com.wipro.assignment.mvvm.network.api.ApiEndPoints.ABOUT_API
import com.wipro.assignment.mvvm.about.model.AboutApiResponse
import retrofit2.Response
import retrofit2.http.GET
// Here apis interface we calling, for each api we have to define the function.
interface ApiClient {
    @GET(ABOUT_API)
    suspend fun getData() : Response<AboutApiResponse>
}