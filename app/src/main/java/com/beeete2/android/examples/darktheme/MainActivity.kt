package com.beeete2.android.examples.darktheme

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.beeete2.android.examples.darktheme.presentation.ui.settings.SettingsFragment
import com.beeete2.android.examples.darktheme.presentation.ui.settings.darktheme.DarkThemeFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_settings
                )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId ?: 0) {
        android.R.id.home -> {
            navController.navigateUp()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    @Module
    abstract class MainActivityModule {
        @Binds
        abstract fun providesActivity(mainActivity: MainActivity): FragmentActivity

        @ContributesAndroidInjector(
            modules = [SettingsFragment.SettingsFragmentModule::class]
        )
        abstract fun contributeSettingsFragment(): SettingsFragment

        @ContributesAndroidInjector(
            modules = [DarkThemeFragment.DarkThemeFragmentModule::class]
        )
        abstract fun contributeDarkThemeFragment(): DarkThemeFragment
    }

    @Module
    abstract class MainActivityBuilder {
        @ContributesAndroidInjector(modules = [MainActivityModule::class])
        abstract fun contributeMainActivity(): MainActivity
    }
}
