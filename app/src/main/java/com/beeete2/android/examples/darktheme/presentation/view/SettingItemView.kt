package com.beeete2.android.examples.darktheme.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.beeete2.android.examples.darktheme.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class SettingItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val clickOverlay by lazy { findViewById<View>(R.id.click_overlay) }
    private val titleView by lazy { findViewById<TextView>(R.id.item_title) }
    private val summaryView by lazy { findViewById<TextView>(R.id.item_summary) }
    private val iconView by lazy { findViewById<ImageView>(R.id.item_icon) }

    init {
        inflate(context, R.layout.view_setting_item, this)
    }

    @TextProp
    fun setTitle(title: CharSequence?) {
        titleView.text = title
    }

    @TextProp
    fun setSummary(summary: CharSequence?) {
        summaryView.text = summary
    }

    @JvmOverloads
    @ModelProp
    fun setIcon(@DrawableRes drawableRes: Int = R.drawable.brightness) {
        iconView.setImageDrawable(ContextCompat.getDrawable(context, drawableRes))
    }

    @CallbackProp
    fun onClickListener(listener: OnClickListener?) {
        clickOverlay.setOnClickListener(listener)
    }

}
