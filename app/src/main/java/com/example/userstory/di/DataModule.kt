package com.example.userstory.di

import android.content.Context
import com.example.userstory.data.datasource.local.LocalRepositoryImpl
//import androidx.multidex.BuildConfig
import com.example.userstory.data.datasource.remote.RestApi
import com.example.userstory.data.datasource.remote.RestRepositoryImpl
import com.example.userstory.domain.repository.LocalRepository
import com.example.userstory.domain.repository.RestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val BASE_URL: String = "https://api.github.com/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideRestApi(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, @IoDispatcher dispatcher: CoroutineDispatcher): RestRepository {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return RestRepositoryImpl(retrofit.create(RestApi::class.java), dispatcher)
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)

//        if (BuildConfig.DEBUG) {
//            builder.addInterceptor(loggingInterceptor)
//        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(@ApplicationContext context: Context): LocalRepository {
        return LocalRepositoryImpl.getInstance(context)
    }
}