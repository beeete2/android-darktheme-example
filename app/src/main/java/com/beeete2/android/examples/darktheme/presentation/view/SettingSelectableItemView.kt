package com.beeete2.android.examples.darktheme.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.beeete2.android.examples.darktheme.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class SettingSelectableItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val clickOverlay by lazy { findViewById<View>(R.id.click_overlay) }
    private val titleView by lazy { findViewById<TextView>(R.id.item_title) }
    private val doneView by lazy { findViewById<ImageView>(R.id.item_done) }

    init {
        inflate(context, R.layout.view_setting_selectable_item, this)
    }

    @TextProp
    fun setTitle(title: CharSequence?) {
        titleView.text = title
    }

    @JvmOverloads
    @ModelProp
    fun setDone(done: Boolean = false) {
        doneView.visibility = if (done) View.VISIBLE else View.INVISIBLE
    }

    @CallbackProp
    fun onClickListener(listener: View.OnClickListener?) {
        clickOverlay.setOnClickListener(listener)
    }

}