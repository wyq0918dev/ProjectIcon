package com.wyq0918dev.projecticon

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

abstract class BaseAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_layout)

        val appIcon: ImageView = findViewById(R.id.appIcon)
        val appName: TextView = findViewById(R.id.appName)
        val foregroundColor: TextView = findViewById(R.id.foregroundColor)
        val backgroundColor: TextView = findViewById(R.id.backgroundColor)
        val note: TextView = findViewById(R.id.note)



        appIcon.setImageDrawable(ResourcesCompat.getDrawable(resources, getAppIcon(), theme))
        appName.text = getString(getAppName())
        foregroundColor.text = getString(R.string.foreground_text) + getColorHexString(getForegroundColor())
        backgroundColor.text = getString(R.string.background_text) + getColorHexString(getBackgroundColor())

    }

    private fun getColorHexString(@ColorRes color: Int): String {
        val color = ResourcesCompat.getColor(resources, getForegroundColor(), theme)
        val hexString = color.toHexString()
        val caps = hexString.toUpperCase()
        return "#$caps"
    }

    @DrawableRes
    abstract fun getAppIcon(): Int
    @StringRes
    abstract fun getAppName(): Int
    @ColorRes
    abstract fun getForegroundColor(): Int
    @ColorRes
    abstract fun getBackgroundColor(): Int
    @StringRes
    abstract fun getNote(): Int
}