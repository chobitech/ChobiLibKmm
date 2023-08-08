package com.chobitech.lib.kmm

import kotlin.random.Random

internal actual fun getDefaultRandom(): Random {
    return Random(System.currentTimeMillis())
}