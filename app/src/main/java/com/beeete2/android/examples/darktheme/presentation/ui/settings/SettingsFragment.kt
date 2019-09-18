package com.beeete2.android.examples.darktheme.presentation.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.TypedEpoxyController
import com.beeete2.android.examples.darktheme.R
import com.beeete2.android.examples.darktheme.di.ViewModelKey
import com.beeete2.android.examples.darktheme.domain.entity.SettingsEntity
import com.beeete2.android.examples.darktheme.presentation.ui.toStringRes
import com.beeete2.android.examples.darktheme.presentation.view.settingItemView
import dagger.Binds
import dagger.Module
import dagger.android.support.DaggerFragment
import dagger.multibindings.IntoMap
import javax.inject.Inject

class SettingsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SettingsViewModel by viewModels { viewModelFactory }

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
        viewModel.settings.observe(viewLifecycleOwner) {
            controller.setData(it)
        }
    }

    private inner class Controller : TypedEpoxyController<SettingsEntity>() {
        override fun buildModels(data: SettingsEntity?) {
            data ?: return

            settingItemView {
                id("dark_theme")
                title(R.string.setting_item_dark_theme)
                summary(data.darkTheme.toStringRes())
                onClickListener { _ -> navigate(R.id.action_navigation_settings_to_settings_dark_theme) }
            }
        }
    }

    protected fun navigate(@IdRes id: Int) {
        findNavController().navigate(id)
    }

    @Module
    abstract class SettingsFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(SettingsViewModel::class)
        internal abstract fun provideViewModel(viewModel: SettingsViewModel): ViewModel
    }
}
