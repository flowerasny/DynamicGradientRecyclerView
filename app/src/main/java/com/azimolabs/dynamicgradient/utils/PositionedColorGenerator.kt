package com.azimolabs.dynamicgradient.utils

import android.graphics.Color

class PositionedColorGenerator(
    private val startingColor: Int,
    private val endColor: Int
) {
    fun getColor(position: Float) = Color.argb(
        colorPositioned(Color.alpha(startingColor), Color.alpha(endColor), position),
        colorPositioned(Color.red(startingColor), Color.red(endColor), position),
        colorPositioned(Color.green(startingColor), Color.green(endColor), position),
        colorPositioned(Color.blue(startingColor), Color.blue(endColor), position)
    )

    private fun colorPositioned(start: Int, end: Int, position: Float) =
        ((1 - position) * start + position * end).toInt()
}