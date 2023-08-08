package com.chobitech.lib.kmm


/*
expect class DateTime {

    companion object {
        val currentUnixEpochMilliSec: Long
    }

    val unixEpochMilliSec: Long

    val localizedDateTimeString: String

}
*/

expect class DateTime {

    companion object {
        fun getCurrentMilliSec(): Long
    }

    val time: Long
    fun getValue(component: DateComponent): Int

    //fun getGap(from: DateTime, component: DateComponent): Int

    constructor()
    constructor(time: Long)
    constructor(year: Int, month: Int, day: Int, hour: Int, minute: Int, sec: Int, milliSec: Int)

    override fun toString(): String
}

enum class DateComponent {
    Year, Month, Day, Hour, Minute, Second, DayOfWeek, MilliSec
}

val DateTime.Companion.now: DateTime
    get() = DateTime()

val DateTime.year get() = getValue(DateComponent.Year)
val DateTime.month get() = getValue(DateComponent.Month)
val DateTime.day get() = getValue(DateComponent.Day)
val DateTime.hour get() = getValue(DateComponent.Hour)
val DateTime.minute get() = getValue(DateComponent.Minute)
val DateTime.second get() = getValue(DateComponent.Second)
val DateTime.milliSec get() = getValue(DateComponent.MilliSec)
val DateTime.dayOfWeek get() = getValue(DateComponent.DayOfWeek)

val DateTime.stringYmd: String
    get() = "${year}/${month.twoDigitsString}/${day.twoDigitsString}"

val DateTime.stringYmdHms: String
    get() = "$stringYmd ${hour.twoDigitsString}:${minute.twoDigitsString}:${second.twoDigitsString}"

val DateTime.stringYmdHmsMs: String
    get() = "$stringYmdHms.${milliSec.getDigitedString(3)}"


fun DateTime.Companion.isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}

fun DateTime.Companion.getDaysOfMonths(year: Int): List<Int> {
    return listOf(31,
        if (isLeapYear(year)) 29 else 28,
        31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
}

fun DateTime.Companion.calcGaps(from: DateTime, to: DateTime, component: DateComponent): Double {
    val msGap = (to.time - from.time).toDouble()
    return when (component) {
        DateComponent.Year -> {
            var year = (to.year - from.year).toDouble()

            val fromDays = getDaysOfMonths(from.year)
            val fromTotalDays = fromDays.sum().toDouble()
            val iFromMonth = from.month - 1
            val fromElapsedDays = (0 until iFromMonth).fold(0) { td, d ->
                td + fromDays[d]
            } + from.day
            year -= fromElapsedDays / fromTotalDays

            val toDays = getDaysOfMonths(to.year)
            val toTotalDays = toDays.sum().toDouble()
            val iToMonth = to.month - 1
            val toElapsedDays = (0 until iToMonth).fold(0) { td, d ->
                td + toDays[d]
            } + to.day
            year += toElapsedDays / toTotalDays

            year
        }
        DateComponent.Month -> {
            var month = 0.0
            for (y in from.year until to.year) {
                month += 12.0
            }

            //--- reduce
            val fromDays = getDaysOfMonths(from.year)
            val iFromMonth = from.month - 1
            month -= iFromMonth
            month -= from.day / fromDays[iFromMonth].toDouble()

            //--- add
            val toDays = getDaysOfMonths(to.year)
            val iToMonth = to.month - 1
            month += iToMonth
            month += to.day / toDays[iToMonth].toDouble()

            month
        }
        DateComponent.Day -> msGap / (24 * 60 * 60 * 1000.0)
        DateComponent.Hour -> msGap / (60 * 60 * 1000.0)
        DateComponent.Minute -> msGap / (60 * 1000.0)
        DateComponent.Second -> msGap / 1000.0
        DateComponent.MilliSec -> msGap
        else -> 0.0
    }
}

val DateTime.daysOfAllMonthsInThisYear: List<Int>
    get() = DateTime.getDaysOfMonths(this.year)

val DateTime.totalDays: Int
    get() = this.daysOfAllMonthsInThisYear.sum()

fun DateTime.calcGapFrom(from: DateTime, component: DateComponent): Double = DateTime.calcGaps(from, this, component)
fun DateTime.calcGapTo(to: DateTime, component: DateComponent): Double = DateTime.calcGaps(this, to, component)

