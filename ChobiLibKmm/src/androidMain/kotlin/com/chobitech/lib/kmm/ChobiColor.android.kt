package com.chobitech.lib.kmm

import android.graphics.Color

val ChobiColor.androidIntColor: Int get() = Color.parseColor(argbHex)
