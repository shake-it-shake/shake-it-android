package com.semicolon.di

import com.semicolon.data.interceptor.AuthorizationInterceptor
import com.semicolon.data.remote.api.FriendApi
import com.semicolon.data.remote.api.ImageApi
import com.semicolon.data.remote.api.RoomApi
import com.semicolon.data.remote.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://3.39.78.140:3000"

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpclient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authorizationInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    fun provideRoomApi(
        retrofit: Retrofit
    ): RoomApi = retrofit.create(RoomApi::class.java)

    @Provides
    fun provideFriendApi(
        retrofit: Retrofit
    ): FriendApi = retrofit.create(FriendApi::class.java)

    @Provides
    fun provideImageApi(
        retrofit: Retrofit
    ): ImageApi = retrofit.create(ImageApi::class.java)
}