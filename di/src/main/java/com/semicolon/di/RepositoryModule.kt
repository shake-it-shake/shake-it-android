package com.semicolon.di

import com.semicolon.data.repository.FriendRepositoryImpl
import com.semicolon.data.repository.RoomRepositoryImpl
import com.semicolon.data.repository.UserRepositoryImpl
import com.semicolon.domain.repository.FriendRepository
import com.semicolon.domain.repository.RoomRepository
import com.semicolon.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindRoomRepository(
        roomRepositoryImpl: RoomRepositoryImpl
    ): RoomRepository

    @Binds
    abstract fun bindFriendRepository(
        friendRepositoryImpl: FriendRepositoryImpl
    ): FriendRepository
}