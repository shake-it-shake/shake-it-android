package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalAuthDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.domain.entity.user.*
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localAuthDataSource: LocalAuthDataSource,
    private val remoteUserDataSource: RemoteUserDataSource
) : UserRepository {

    override suspend fun autoLogin() {
        val account = localAuthDataSource.fetchAccount()
        remoteUserDataSource.login(account)
    }

    override suspend fun login(loginEntity: LoginEntity) {
        val token = remoteUserDataSource.login(loginEntity)
        localAuthDataSource.saveToken(token)
        localAuthDataSource.saveAccount(loginEntity)
    }

    override suspend fun signUp(signUpEntity: SignUpEntity) {
        val token = remoteUserDataSource.signUp(signUpEntity)
        localAuthDataSource.saveToken(token)
        localAuthDataSource.saveAccount(
            LoginEntity(id = signUpEntity.id, password = signUpEntity.password)
        )
    }

    override suspend fun removeAccount() =
        remoteUserDataSource.removeAccount()

    override suspend fun editProfile(editProfileEntity: EditProfileEntity) =
        remoteUserDataSource.editProfile(editProfileEntity)

    override suspend fun fetchProfile(fetchProfileEntity: FetchProfileEntity): ProfileEntity =
        remoteUserDataSource.fetchProfile(fetchProfileEntity)

    override suspend fun fetchMyProfile(): ProfileEntity {
        val id = localAuthDataSource.fetchAccount().id
        return remoteUserDataSource.fetchProfile(FetchProfileEntity(id))
    }

}
