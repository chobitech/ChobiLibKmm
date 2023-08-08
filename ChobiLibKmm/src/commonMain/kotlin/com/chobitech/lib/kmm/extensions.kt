package com.chobitech.lib.kmm

val Byte.hexTwoDigits: String
    get() = toUByte().toString(16).let { s ->
        when (s.length < 2) {
            true -> "0$s"
            false -> s
        }
    }


fun Int.getDigitedString(digits: Int): String = this.toString().let { s ->
    when {
        s.length < digits -> "${ Array(digits - s.length) { 0 }.joinToString("") }$s"
        else -> s
    }
}

val Int.twoDigitsString: String get() = getDigitedString(2)
val String.stringArrayList: List<String> get() = this.toCharArray().map { it.toString() }
fun <T : Comparable<T>> T.clamp(minVal: T, maxVal: T): T {
    return minOf(maxOf(this, minVal), maxVal)
}

val Float.clamp01: Float get() = this.clamp(0f, 1f)
val Float.uByte: UByte get() = (this.clamp01 * 255).toInt().toUByte()
val UByte.float: Float get() = this.toFloat() / 255.0f
val UByte.hex: String get() = this.toString(16).let { h ->
    when {
        h.length < 2 -> "0$h"
        else -> h
    }
}
val Byte.hex: String get() = this.toUByte().hex

val Boolean.intValue: Int
    get() = when (this) {
        true -> 1
        false -> 0
    }

fun Float.getIntPart(): Int = this.toInt()


fun <T> Boolean.getBranchedValue(whenTrue: T, whenFalse: T) = when (this) {
    true -> whenTrue
    false -> whenFalse
}

fun <T> Boolean.getBranchedValue(whenTrueFunc: (Boolean) -> T, whenFalseFunc: (Boolean) -> T) = this.getBranchedValue(whenTrueFunc(this), whenFalseFunc(this))

fun Int.hasFlag(flag: Int): Boolean {
    return (this and  flag) == flag
}

fun <T : Number> T.calcPositionRate(minVal: T, maxVal: T): Float {
    val fMin = minVal.toFloat()
    return ((this.toFloat() - fMin) / (maxVal.toFloat() - fMin)).coerceIn(0f, 1f)
}

