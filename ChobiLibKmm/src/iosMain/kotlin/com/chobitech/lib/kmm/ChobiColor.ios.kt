package com.chobitech.lib.kmm

import platform.UIKit.UIColor

val ChobiColor.uiColor: UIColor get() = UIColor(red = red.cgFloat, green = green.cgFloat, blue = blue.cgFloat, alpha = alpha.cgFloat)
