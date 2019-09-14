package com.beeete2.android.examples.darktheme.di

import android.content.Context
import com.beeete2.android.examples.darktheme.App
import com.beeete2.android.examples.darktheme.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        MainActivity.MainActivityBuilder::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}
