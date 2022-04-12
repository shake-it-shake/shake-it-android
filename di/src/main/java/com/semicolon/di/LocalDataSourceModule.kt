package com.semicolon.di

import com.semicolon.data.local.datasource.LocalAuthDataSource
import com.semicolon.data.local.datasource.LocalAuthDataSourceImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    abstract fun bindLocalAuthDataSource(
        localAuthDataSourceImpl: LocalAuthDataSourceImpl
    ): LocalAuthDataSource
}