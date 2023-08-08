package com.chobitech.lib.kmm

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970
import kotlin.random.Random

internal actual fun getDefaultRandom(): Random {
    val epochSec = NSDate().timeIntervalSince1970
    return Random((epochSec * 1000).toLong())
}
