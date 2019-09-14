package com.beeete2.android.examples.darktheme.presentation.ui.settings.darktheme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.TypedEpoxyController
import com.beeete2.android.examples.darktheme.R
import com.beeete2.android.examples.darktheme.di.ViewModelKey
import com.beeete2.android.examples.darktheme.domain.entity.SettingsEntity
import com.beeete2.android.examples.darktheme.domain.value.SettingDarkTheme
import com.beeete2.android.examples.darktheme.presentation.view.settingSelectableItemView
import dagger.Binds
import dagger.Module
import dagger.android.support.DaggerFragment
import dagger.multibindings.IntoMap
import javax.inject.Inject

class DarkThemeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DarkThemeViewModel by viewModels { viewModelFactory }

    private val controller = Controller()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false).apply {
            val recyclerView = findViewById<EpoxyRecyclerView>(R.id.recycler_view)
            recyclerView.apply {
                setController(controller)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.settings.observe(this) {
            controller.setData(it)
        }
    }

    private inner class Controller : TypedEpoxyController<SettingsEntity>() {
        override fun buildModels(data: SettingsEntity?) {
            data ?: return

            settingSelectableItemView {
                id("system_default")
                title(R.string.item_dark_theme_system_default)
                done(data.darkTheme == SettingDarkTheme.SYSTEM_DEFAULT)
                onClickListener { _ ->
                    viewModel.saveDarkTheme(SettingDarkTheme.SYSTEM_DEFAULT)
                    AppCompatDelegate.setDefaultNightMode(SettingDarkTheme.SYSTEM_DEFAULT.toNightMode())
                }
            }
            settingSelectableItemView {
                id("day")
                title(R.string.item_dark_theme_day)
                done(data.darkTheme == SettingDarkTheme.DAY)
                onClickListener { _ ->
                    viewModel.saveDarkTheme(SettingDarkTheme.DAY)
                    AppCompatDelegate.setDefaultNightMode(SettingDarkTheme.DAY.toNightMode())
                }
            }
            settingSelectableItemView {
                id("night")
                title(R.string.item_dark_theme_night)
                done(data.darkTheme == SettingDarkTheme.NIGHT)
                onClickListener { _ ->
                    viewModel.saveDarkTheme(SettingDarkTheme.NIGHT)
                    AppCompatDelegate.setDefaultNightMode(SettingDarkTheme.NIGHT.toNightMode())
                }
            }
        }
    }

    @Module
    abstract class DarkThemeFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(DarkThemeViewModel::class)
        internal abstract fun provideViewModel(viewModel: DarkThemeViewModel): ViewModel
    }

    private fun SettingDarkTheme.toNightMode() = when(this) {
        SettingDarkTheme.SYSTEM_DEFAULT -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        SettingDarkTheme.DAY -> AppCompatDelegate.MODE_NIGHT_NO
        SettingDarkTheme.NIGHT -> AppCompatDelegate.MODE_NIGHT_YES
    }

}
