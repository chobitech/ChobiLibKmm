package com.chobitech.lib.kmm

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object DebugMsg {

    private var logNumber: ULong = 0u

    var tag: String = "==DEBUG=="
    var isDebug = true

    private fun prt(mark: String, o: Any?) {
        if (isDebug) {
            println(
                StringBuilder()
                    .append("[")
                    .append(mark)
                    .append("]")
                    .also {
                        if (tag.isNotEmpty()) {
                            it.append("[").append(tag).append("]")
                        }
                    }
                    .append("[")
                    .append((++logNumber).toString().padStart(5, '0'))
                    .append("] ")
                    .append(o)
                    .append(" (")
                    .append(DateTime().toString())
                    .append(")")
            )
        }
    }

    fun i(o: Any?) {
        prt("I", o)
    }
    fun w(o: Any?) {
        prt("W", o)
    }
    fun e(o: Any?) {
        prt("E", o)
    }
}