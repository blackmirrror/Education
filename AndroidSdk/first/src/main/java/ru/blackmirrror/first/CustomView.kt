package ru.blackmirrror.first

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.Random

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var fillPercentage = 0
    private val random = Random()
    private val paint = Paint().apply {
        color = getRandomColor()
        style = Paint.Style.FILL
    }

    init {
        setOnClickListener {
            updateFill()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val fillWidth = width * fillPercentage / 100 // Теперь расчет по ширине
        canvas.drawRect(0f, 0f, fillWidth.toFloat(), height.toFloat(), paint)
    }

    private fun updateFill() {
        fillPercentage += 10
        if (fillPercentage > 100) {
            fillPercentage = 10 // Начать заново после 100%
        }
        paint.color = getRandomColor()
        invalidate() // Перерисовываем View
    }

    private fun getRandomColor(): Int {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}

