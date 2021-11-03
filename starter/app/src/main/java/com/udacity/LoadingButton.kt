package com.udacity

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var widthSize = 0
    private var heightSize = 0

    private val valueAnimator = ValueAnimator()
    private val buttonBackgroundPaint = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        color = ContextCompat.getColor(context, R.color.buttonBackground)
    }

    private val buttonTextPaint = Paint().apply {
        isAntiAlias = true
        color = ContextCompat.getColor(context, R.color.white)
        textSize = 50.0f
    }
    private var buttonState: ButtonState by Delegates.observable<ButtonState>(ButtonState.Completed) { p, old, new ->

    }


    init {

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(ContextCompat.getColor(context, R.color.buttonBackground))
        canvas?.drawRect(0f, 0f, 100f, 100f, buttonBackgroundPaint )
       // if (buttonState == ButtonState.Loading) {
            canvas?.drawRect(0f, 0f, 100f, 100f, buttonBackgroundPaint)
       // }
        canvas?.drawText("Download", 200.0f, 75.0f, buttonTextPaint)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
        widthSize = w
        heightSize = h
        setMeasuredDimension(w, h)
    }

}