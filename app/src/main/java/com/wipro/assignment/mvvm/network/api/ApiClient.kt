package com.wipro.assignment.mvvm.network.api
import com.wipro.assignment.mvvm.network.api.ApiEndPoints.ABOUT_API
import com.wipro.assignment.mvvm.repository.data.AboutApiResponse
import retrofit2.http.GET
interface ApiClient {
    @GET(ABOUT_API)
    suspend fun getData() : AboutApiResponse
}