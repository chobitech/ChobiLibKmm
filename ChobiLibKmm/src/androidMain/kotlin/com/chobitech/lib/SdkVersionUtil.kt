@file:Suppress("unused")

package com.chobitech.lib

import android.annotation.SuppressLint
import android.os.Build

object SdkVersionUtil {

    val apiVer: Int
        get() = Build.VERSION.SDK_INT


    @SuppressLint("AnnotateVersionCheck")
    fun apiIsGreaterThanOrEqual(apiLevel: Int): Boolean {
        return Build.VERSION.SDK_INT >= apiLevel
    }

    @SuppressLint("AnnotateVersionCheck")
    fun apiIsGreaterThan(apiLevel: Int): Boolean {
        return Build.VERSION.SDK_INT > apiLevel
    }

    @SuppressLint("AnnotateVersionCheck")
    fun apiIsLessThan(apiLevel: Int): Boolean {
        return Build.VERSION.SDK_INT < apiLevel
    }

    @SuppressLint("AnnotateVersionCheck")
    fun apiIsLessThanOrEqual(apiLevel: Int): Boolean {
        return Build.VERSION.SDK_INT <= apiLevel
    }

    @SuppressLint("AnnotateVersionCheck")
    fun apiIsEqual(apiLevel: Int): Boolean {
        return Build.VERSION.SDK_INT == apiLevel
    }

    fun <T> onSdkVersionGreaterThanOrEqual(sdkVer: Int, inTrue: (() -> T)?, inFalse: (() -> T)?): T? = checkSdkVersionAndProcess(sdkVer,
        { apiIsGreaterThanOrEqual(it) },
        inTrue, inFalse)

    fun <T> onSdkVersionLessThanOrEqual(sdkVer: Int, inTrue: (() -> T)?, inFalse: (() -> T)?): T? = checkSdkVersionAndProcess(sdkVer,
        { apiIsLessThanOrEqual(it) },
        inTrue, inFalse)

    fun <T> onSdkVersionGreaterThan(sdkVer: Int, inTrue: (() -> T)?, inFalse: (() -> T)?): T? = checkSdkVersionAndProcess(sdkVer,
        { apiIsGreaterThan(it) },
        inTrue, inFalse)

    fun <T> onSdkVersionLessThan(sdkVer: Int, inTrue: (() -> T)?, inFalse: (() -> T)?): T? = checkSdkVersionAndProcess(sdkVer,
        { apiIsLessThan(it) },
        inTrue, inFalse)

    fun <T> onSdkVersionEqual(sdkVer: Int, inTrue: (() -> T)?, inFalse: (() -> T)?): T? = checkSdkVersionAndProcess(sdkVer,
        { apiIsEqual(it) },
        inTrue, inFalse)

    private fun <T> checkSdkVersionAndProcess(sdkVer: Int, checker: (Int) -> Boolean, inTrue: (() -> T)?, inFalse: (() -> T)?): T? = when {
        checker(sdkVer) -> inTrue?.invoke()
        else -> inFalse?.invoke()
    }

}