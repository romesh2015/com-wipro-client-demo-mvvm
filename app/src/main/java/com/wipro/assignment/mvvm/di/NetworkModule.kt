package com.wipro.assignment.mvvm.di
import com.wipro.assignment.mvvm.utility.AppConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
/**
 *  This class used for network operations with retrofit with coroutine & Rx Java
 */
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun  provideCoroutineRetroInfo(okHttpClient: OkHttpClient) : Retrofit {
        return  Retrofit
            .Builder()
            .baseUrl(AppConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(AppConfig.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(AppConfig.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(AppConfig.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(MyInterceptor())
        return  httpClient.build();
    }
    class MyInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")

            val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }
}