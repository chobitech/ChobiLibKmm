package com.chobitech.lib.kmm

import kotlin.random.Random

class RandomString(seeds: List<String>) : Randomize<String>(seeds) {

    companion object {

        private val digits
            get() = "0123456789"

        private val hexChars
            get() = "abcdef"

        private val nonHexChars
            get() = "ghijklmnopqrstuvwxyz"


        fun getDigitsOnly(): RandomString {
            return RandomString(digits)
        }

        fun getLowerHex(): RandomString {
            return RandomString(digits + hexChars)
        }

        fun getUpperHex(): RandomString {
            return RandomString(digits + hexChars.uppercase())
        }

        fun getHex(): RandomString {
            return RandomString(digits + hexChars + hexChars.uppercase())
        }

        fun getLowerChars(): RandomString {
            return RandomString(hexChars + nonHexChars)
        }

        fun getUpperChars(): RandomString {
            return RandomString((hexChars + nonHexChars).uppercase())
        }

        fun getChars(): RandomString {
            val allChars = hexChars + nonHexChars
            return RandomString(allChars + allChars.uppercase())
        }

        fun getLowerCharDigits(): RandomString {
            return RandomString(digits + hexChars + nonHexChars)
        }
        fun getUpperCharDigits(): RandomString {
            return RandomString(digits + (hexChars + nonHexChars).uppercase())
        }
        fun getCharDigits(): RandomString {
            val allChars = hexChars + nonHexChars
            return RandomString(digits + allChars + allChars.uppercase())
        }
    }

    constructor(seedString: String) : this(seedString.toCharArray().map { it.toString() })


    fun getRandomString(length: Int, duplication: Boolean = true): String {
        return getRandomList(length, duplication).joinToString("")
    }

}
