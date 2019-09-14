package com.beeete2.android.examples.darktheme.di

import com.beeete2.android.examples.darktheme.data.repository.SettingsRepository
import com.beeete2.android.examples.darktheme.domain.usecase.SettingsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideSettingsUseCase(settingsRepository: SettingsRepository): SettingsUseCase =
        SettingsUseCase(settingsRepository)

}