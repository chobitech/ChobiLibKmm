package com.chobitech.lib.kmm

import kotlin.math.min
import kotlin.random.Random

internal expect fun getDefaultRandom(): Random

open class Randomize<T>(val seeds: List<T>, val rand: Random = getDefaultRandom()) {

    fun next(): T {
        return seeds.random(rand)
    }

    fun getRandomList(length: Int, duplication: Boolean = true): List<T> {
        return ArrayList<T>().also { arr ->
            when (duplication) {
                true -> (0 until length).forEach { _ ->
                    arr.add(next())
                }
                false -> {
                    val tmpArr = ArrayList(seeds)
                    val limit = min(length, tmpArr.size)
                    (0 until limit).forEach { _ ->
                        val n = rand.nextInt(tmpArr.size)
                        arr.add(tmpArr.removeAt(n))
                    }
                }
            }
        }
    }
}