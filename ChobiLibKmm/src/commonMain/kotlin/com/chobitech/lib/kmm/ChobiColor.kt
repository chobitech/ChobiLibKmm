package com.chobitech.lib.kmm

open class ChobiColor {

    companion object {
        val transparent get() = ChobiColor(0f, 0f, 0f, 0f)
        val white get() = ChobiColor((0xffffffff).toInt())
        val black get() = ChobiColor(0f, 0f, 0f, 1f)
        val red get() = ChobiColor(1f, 0f, 0f, 1f)
        val green get() = ChobiColor("#008000")
        val blue get() = ChobiColor(0f, 0f, 1f, 1f)
        val tomato get() = ChobiColor("#ff6347")

        val hexColorRegex = Regex("^#([0-9a-f]{8}|[0-9a-f]{6}|[0-9a-f]{3})$", RegexOption.IGNORE_CASE)

        private fun getFloat(hex: String): Float = hex.toUByte(16).float
        private fun getFloat(hex: String, index: Int): Float = getFloat(hex.substring(index, index + 2))

        private fun parseHex(hex: String): FloatArray {
            return hexColorRegex.matchEntire(hex)?.let { m ->
                val s = m.groupValues[1]
                when (s.length) {
                    8 -> floatArrayOf(
                        getFloat(s, 2),
                        getFloat(s, 4),
                        getFloat(s, 6),
                        getFloat(s, 0)
                    )
                    6 -> floatArrayOf(
                        getFloat(s, 0),
                        getFloat(s, 2),
                        getFloat(s, 4),
                        1f
                    )
                    3 -> floatArrayOf(
                        getFloat("${s[0]}${s[0]}"),
                        getFloat("${s[1]}${s[1]}"),
                        getFloat("${s[2]}${s[2]}"),
                        1f
                    )
                    else -> null
                }
            } ?: throw NumberFormatException()
        }
    }

    var red: Float
    var green: Float
    var blue: Float
    var alpha: Float

    var redUByte: UByte
        get() = red.uByte
        set(v) { red = v.float }

    var greenUByte: UByte
        get() = green.uByte
        set(v) { green = v.float }

    var blueUByte: UByte
        get() = blue.uByte
        set(v) { blue = v.float }

    var alphaUByte: UByte
        get() = alpha.uByte
        set(v) { alpha = v.float }


    private fun getFloatFromHex(hex: String): Float? = hex.toByteOrNull(16)?.let { it.toUByte().float }

    var redHex: String
        get() = redUByte.hex
        set(v) { getFloatFromHex(v)?.let { red = it } }

    var greenHex: String
        get() = greenUByte.hex
        set(v) { getFloatFromHex(v)?.let { green = it } }

    var blueHex: String
        get() = blueUByte.hex
        set(v) { getFloatFromHex(v)?.let { blue = it } }

    var alphaHex: String
        get() = alphaUByte.hex
        set(v) { getFloatFromHex(v)?.let { alpha = it } }

    private val rgbStr
        get() = "${redHex}${greenHex}${blueHex}"

    val rgbHex
        get() = "#$rgbStr"

    val argbHex
        get() = "#$alphaHex$rgbStr"

    val argbLong: Long
        get() = (alphaUByte.toLong() shl 24) + (redUByte.toLong() shl 16) + (greenUByte.toLong() shl 8) + blueUByte.toLong()

    constructor(red: Float, green: Float, blue: Float, alpha: Float = 1f) {
        this.red = red
        this.green = green
        this.blue = blue
        this.alpha = alpha
    }

    constructor(hex: String) {
        val fArr = parseHex(hex)
        this.red = fArr[0]
        this.green = fArr[1]
        this.blue = fArr[2]
        this.alpha = fArr[3]
    }

    private fun intToByte(v: Int, shift: Int): UByte {
        return ((v shr shift) and 0xff).toUByte()
    }

    constructor(argbInt: Int) {
        this.red = intToByte(argbInt, 16).float
        this.green = intToByte(argbInt, 8).float
        this.blue = intToByte(argbInt, 0).float
        this.alpha = intToByte(argbInt, 24).float
    }

    override fun equals(other: Any?): Boolean {
        return (other as? ChobiColor)?.let {
            (it.red == red) && (it.green == green) && (it.blue == blue) && (it.alpha == alpha)
        } ?: false
    }
}