package com.ardakazanci.gong.data.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {
    @Provides
    @Singleton
    fun getContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }
}
