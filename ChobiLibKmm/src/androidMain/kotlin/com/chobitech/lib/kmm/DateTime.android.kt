package com.chobitech.lib.kmm

import java.util.Calendar
import java.util.Date

actual class DateTime {

    actual companion object {
        actual fun getCurrentMilliSec(): Long {
            return System.currentTimeMillis()
        }
    }

    var javaDate: Date
        private set

    actual val time: Long
        get() = javaDate.time

    actual fun getValue(component: DateComponent): Int {
        return Calendar.getInstance().let { cald ->
            cald.time = javaDate

            when (component) {
                DateComponent.Year -> cald.get(Calendar.YEAR)
                DateComponent.Month -> cald.get(Calendar.MONTH) + 1
                DateComponent.Day -> cald.get(Calendar.DATE)
                DateComponent.Hour -> cald.get(Calendar.HOUR_OF_DAY)
                DateComponent.Minute -> cald.get(Calendar.MINUTE)
                DateComponent.Second -> cald.get(Calendar.SECOND)
                DateComponent.MilliSec -> cald.get(Calendar.MILLISECOND)
                DateComponent.DayOfWeek -> cald.get(Calendar.DAY_OF_WEEK)
            }
        }
    }

    actual constructor() {
        javaDate = Date()
    }

    actual constructor(time: Long) {
        javaDate = Date(time)
    }

    actual constructor(year: Int, month: Int, day: Int, hour: Int, minute: Int, sec: Int, milliSec: Int) {
        javaDate = Calendar.getInstance().let { cald ->
            cald.set(year, month - 1, day, hour, minute, sec)
            cald.set(Calendar.MILLISECOND, milliSec)
            cald.time
        }
    }

    actual override fun toString(): String = javaDate.toString()
}

/*
import java.util.Date

actual class DateTime(epochMilliSec: Long = currentUnixEpochMilliSec) {

    actual companion object {
        actual val currentUnixEpochMilliSec: Long
            get() = System.currentTimeMillis()
    }

    val javaDate = Date(epochMilliSec)

    actual val unixEpochMilliSec: Long
        get() = javaDate.time



}

 */