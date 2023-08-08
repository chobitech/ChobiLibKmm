package com.chobitech.lib.kmm

import java.util.Locale

internal actual fun isLangJa(): Boolean {

    return Locale.getDefault().language == "ja"

}