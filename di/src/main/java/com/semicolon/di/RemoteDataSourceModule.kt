package com.semicolon.di

import com.semicolon.data.remote.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindRemoteUserDataSource(
        remoteUserDataSourceImpl: RemoteUserDataSourceImpl
    ): RemoteUserDataSource

    @Binds
    abstract fun bindRemoteRoomDataSource(
        remoteRoomDataSourceImpl: RemoteRoomDataSourceImpl
    ): RemoteRoomDataSource

    @Binds
    abstract fun bindRemoteFriendDataSource(
        remoteFriendDataSourceImpl: RemoteFriendDataSourceImpl
    ): RemoteFriendDataSource
}