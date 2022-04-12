package com.semicolon.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

object LocalModule {

    @Provides
    fun provideSharedPreference(
        @ApplicationContext context: Context
    ): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
}