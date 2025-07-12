package com.wyq0918dev.projecticon

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

abstract class BaseAppActivity : AppCompatActivity() {

    @DrawableRes
    abstract fun getAppIcon(): Int

    @StringRes
    abstract fun getAppName(): Int

    @ColorRes
    abstract fun getForegroundColor(): Int

    @ColorRes
    abstract fun getBackgroundColor(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.base_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById<ConstraintLayout>(R.id.rootView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom,
            )
            insets
        }
        findViewById<ImageView>(R.id.appIcon).apply {
            setImageDrawable(ResourcesCompat.getDrawable(resources, getAppIcon(), theme))
        }
        findViewById<TextView>(R.id.appName).apply {
            text = getString(getAppName())
        }
        findViewById<TextView>(R.id.foregroundColor).apply {
            text = getString(
                R.string.foreground_text,
                getColorHexString(getForegroundColor()),
            )
            setBackgroundColor(getColorInt(getForegroundColor()))
        }
        findViewById<TextView>(R.id.backgroundColor).apply {
            text = getString(
                R.string.background_text,
                getColorHexString(getBackgroundColor()),
            )
            setBackgroundColor(getColorInt(getBackgroundColor()))
        }
    }

    private fun getColorHexString(@ColorRes color: Int): String {
        val color = getColorInt(color = color)
        val hex = color.toHexString()
        val caps = hex.uppercase()
        return "#$caps"
    }

    private fun getColorInt(@ColorRes color: Int): Int {
        return ResourcesCompat.getColor(resources, color, theme)
    }
}