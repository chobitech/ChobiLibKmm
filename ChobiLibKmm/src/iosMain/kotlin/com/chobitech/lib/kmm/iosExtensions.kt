package com.chobitech.lib.kmm

import platform.CoreGraphics.CGFloat

val Float.cgFloat: CGFloat
    get() = this.toDouble()
