package com.mungai.intothepotter_verse.di

import com.mungai.intothepotter_verse.common.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @IoDispatcher
    @Provides
    fun providesIoDispatcher() = Dispatchers.IO
}