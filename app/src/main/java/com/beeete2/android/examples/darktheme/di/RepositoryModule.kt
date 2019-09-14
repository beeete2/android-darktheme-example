package com.beeete2.android.examples.darktheme.di

import android.content.Context
import com.beeete2.android.examples.darktheme.data.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSettingsRepository(context: Context): SettingsRepository = SettingsRepository(context)

}
