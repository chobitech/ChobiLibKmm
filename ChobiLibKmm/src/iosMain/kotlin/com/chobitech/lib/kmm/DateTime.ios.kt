package com.chobitech.lib.kmm

import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitDay
import platform.Foundation.NSCalendarUnitHour
import platform.Foundation.NSCalendarUnitMinute
import platform.Foundation.NSCalendarUnitMonth
import platform.Foundation.NSCalendarUnitNanosecond
import platform.Foundation.NSCalendarUnitSecond
import platform.Foundation.NSCalendarUnitWeekday
import platform.Foundation.NSCalendarUnitYear
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSDateFormatterMediumStyle
import platform.Foundation.create
import platform.Foundation.now
import platform.Foundation.timeIntervalSince1970


private val NSDate.timeMs: Long get() = (this.timeIntervalSince1970 * 1000).toLong()

actual class DateTime {

    actual companion object {
        actual fun getCurrentMilliSec(): Long = NSDate.now.timeMs
    }

    var nsDate: NSDate
        private set

    actual val time: Long get() = nsDate.timeMs

    actual fun getValue(component: DateComponent): Int {
        val cald = NSCalendar.currentCalendar
        val comps = NSCalendarUnitYear or
                NSCalendarUnitMonth or
                NSCalendarUnitDay or
                NSCalendarUnitHour or
                NSCalendarUnitMinute or
                NSCalendarUnitSecond or
                NSCalendarUnitNanosecond or
                NSCalendarUnitWeekday

        val dc = cald.components(comps, nsDate)

        return when (component) {
            DateComponent.Year -> dc.year.toInt()
            DateComponent.Month -> dc.month.toInt()
            DateComponent.Day -> dc.day.toInt()
            DateComponent.Hour -> dc.hour.toInt()
            DateComponent.Minute -> dc.minute.toInt()
            DateComponent.Second -> dc.second.toInt()
            DateComponent.MilliSec -> (dc.nanosecond / 1000000.0).toInt()
            DateComponent.DayOfWeek -> dc.weekday.toInt()
        }
    }

    actual constructor() {
        nsDate = NSDate.now
    }
    actual constructor(time: Long) {
        nsDate = NSDate.create(timeIntervalSince1970 = time.toDouble())
    }

    actual constructor(year: Int, month: Int, day: Int, hour: Int, minute: Int, sec: Int, milliSec: Int) {
        val date = NSDate.create(timeIntervalSince1970 = 0.0)
        val cald = NSCalendar.currentCalendar
        val comps = NSCalendarUnitYear or
                NSCalendarUnitMonth or
                NSCalendarUnitDay or
                NSCalendarUnitHour or
                NSCalendarUnitMinute or
                NSCalendarUnitSecond or
                NSCalendarUnitNanosecond or
                NSCalendarUnitWeekday

        val dc = cald.components(comps, date)

        dc.year = year.toLong()
        dc.month = month.toLong()
        dc.day = day.toLong()
        dc.hour = hour.toLong()
        dc.minute = minute.toLong()
        dc.second = sec.toLong()
        dc.nanosecond = milliSec * 1000000L

        nsDate = date
    }

    actual override fun toString(): String {
        val fmt = NSDateFormatter()
        fmt.dateStyle = NSDateFormatterMediumStyle
        fmt.timeStyle = NSDateFormatterMediumStyle

        return fmt.stringFromDate(nsDate)
    }
}


/*
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSRelativeDateTimeFormatter
import platform.Foundation.NSTimeIntervalSince1970
import platform.Foundation.currentLocale
import platform.Foundation.timeIntervalSince1970

actual class DateTime(epochMilliSec: Long = currentUnixEpochMilliSec) {

    actual companion object {
        actual val currentUnixEpochMilliSec: Long
            get() = (NSDate().timeIntervalSince1970 * 1000).toLong()
    }

    val nsDate = NSDate(NSTimeIntervalSince1970 + (epochMilliSec / 1000.0))

    actual val unixEpochMilliSec: Long
        get() = (nsDate.timeIntervalSince1970 * 1000).toLong()

    actual val localizedDateTimeString: String
        get() {
            val dateFormatter = NSRelativeDateTimeFormatter()
            dateFormatter.locale = NSLocale.currentLocale
            return dateFormatter.string
        }

}

 */