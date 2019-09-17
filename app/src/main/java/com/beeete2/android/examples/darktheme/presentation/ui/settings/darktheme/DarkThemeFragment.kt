package com.beeete2.android.examples.darktheme.presentation.ui.settings.darktheme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
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
import com.beeete2.android.examples.darktheme.presentation.ui.toNightMode
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

    private val items = arrayOf(
        DarkThemeItem(SettingDarkTheme.SYSTEM_DEFAULT, R.string.item_dark_theme_system_default),
        DarkThemeItem(SettingDarkTheme.DAY, R.string.item_dark_theme_day),
        DarkThemeItem(SettingDarkTheme.NIGHT, R.string.item_dark_theme_night)
    )

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

            for (item in items) {
                settingSelectableItemView {
                    id(item.darkTheme.name)
                    title(item.title)
                    done(data.darkTheme == item.darkTheme)
                    onClickListener { _ ->
                        viewModel.saveDarkTheme(item.darkTheme)
                        AppCompatDelegate.setDefaultNightMode(item.darkTheme.toNightMode())
                    }
                }
            }
        }
    }

    private data class DarkThemeItem(
        val darkTheme: SettingDarkTheme,
        @StringRes val title: Int
    )

    @Module
    abstract class DarkThemeFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(DarkThemeViewModel::class)
        internal abstract fun provideViewModel(viewModel: DarkThemeViewModel): ViewModel
    }

}
