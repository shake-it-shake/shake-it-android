package com.semicolon.di

import com.semicolon.data.local.preference.AuthPreference
import com.semicolon.data.local.preference.AuthPreferenceImpl
import dagger.Binds

abstract class PreferenceModule {

    @Binds
    abstract fun bindAuthPreference(
        authPreferenceImpl: AuthPreferenceImpl
    ): AuthPreference
}