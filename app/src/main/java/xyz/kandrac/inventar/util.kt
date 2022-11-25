package xyz.kandrac.inventar

import android.app.Activity
import android.content.Intent

inline fun <reified U: Activity> Activity.start() = startActivity(Intent(this, U::class.java))