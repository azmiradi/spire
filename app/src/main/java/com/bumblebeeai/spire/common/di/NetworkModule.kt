package com.bumblebeeai.spire.common.di

import com.azmiradi.android_base.configuration.AppConfiguration
import com.azmiradi.android_base.configuration.AppConfiguration.connectTimeoutSec
import com.azmiradi.android_base.data.data_sources.remote.factory.CustomCallAdapterFactory
import com.azmiradi.android_base.data.data_sources.remote.retrofit.ApiService
import com.azmiradi.android_base.data.data_sources.remote.retrofit.HeadersInterceptor
import com.azmiradi.android_base.data.data_sources.remote.retrofit.RetrofitNetworkProvider
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.azmiradi.kotlin_base.domain.repository.remote.INetworkProvider
import com.bumblebeeai.spire.BuildConfig
import com.bumblebeeai.spire.auth.login.domain.models.keys.KeyValue
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient.Builder,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient.build())
            .baseUrl(AppConfiguration.baseURL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(CustomCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideNetworkProvider(apiService: ApiService): INetworkProvider =
        RetrofitNetworkProvider(apiService)

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @Singleton
    fun provideHeadersInterceptor(storageKeyValue: IStorageKeyValue): HeadersInterceptor {
        return HeadersInterceptor {
            mapOf(
                "Authorization" to "Bearer " + storageKeyValue.getSecuredValue(
                    KeyValue.TOKEN, KeyAliasValue.TOKEN
                )
            )
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headersInterceptor: HeadersInterceptor,
    ): OkHttpClient.Builder =
        OkHttpClient().newBuilder().apply {
            connectTimeout(connectTimeoutSec, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            readTimeout(connectTimeoutSec, TimeUnit.SECONDS)
            writeTimeout(connectTimeoutSec, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(headersInterceptor)
        }
}