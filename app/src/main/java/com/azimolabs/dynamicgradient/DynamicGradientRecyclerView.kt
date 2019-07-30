package com.azimolabs.dynamicgradient

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.getColorOrThrow
import androidx.recyclerview.widget.RecyclerView
import com.azimolabs.dynamicgradient.utils.PositionedColorGenerator

class DynamicGradientRecyclerView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {

    private val positionedColorGenerator: PositionedColorGenerator
    private var gradientId: Int? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DynamicGradientRecyclerView)
        try {
            positionedColorGenerator = PositionedColorGenerator(
                typedArray.getColorOrThrow(R.styleable.DynamicGradientRecyclerView_topGradientColor),
                typedArray.getColorOrThrow(R.styleable.DynamicGradientRecyclerView_bottomGradientColor)
            )
        } finally {
            typedArray.recycle()
        }
    }

    fun <T : ViewHolder> initialize(adapter: Adapter<T>, gradientId: Int) {
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) = colorItemsBackgrounds()
        })
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() = colorItemsBackgrounds()
        })
        this.adapter = adapter
        this.gradientId = gradientId
    }

    fun colorItemsBackgrounds() {
        (0 until childCount).forEach { listPosition ->
            val child = getChildAt(listPosition)
            gradientId?.let {
                child.findViewById<View>(it)?.background = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(
                        positionedColorGenerator.getColor(getScreenYPercentagePosition(child.top)),
                        positionedColorGenerator.getColor(getScreenYPercentagePosition(child.bottom))
                    )
                ).also { it.cornerRadii = floatArrayOf(90F, 90F, 90F, 90F, 16F, 16F, 90F, 90F) }
            }
        }
    }

    private fun getScreenYPercentagePosition(position: Int) = when {
        position <= 0 -> 0
        position > (top + bottom) -> (top + bottom)
        else -> position
    }.toFloat() / (top + bottom)
}