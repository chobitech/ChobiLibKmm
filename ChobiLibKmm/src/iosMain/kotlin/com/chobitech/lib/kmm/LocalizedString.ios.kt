package com.chobitech.lib.kmm

import platform.Foundation.NSLocale
import platform.Foundation.componentsFromLocaleIdentifier
import platform.Foundation.preferredLanguages

internal actual fun isLangJa(): Boolean {

    return (NSLocale.preferredLanguages.firstOrNull() as? String)?.let {
        val dic = NSLocale.componentsFromLocaleIdentifier(it)
        dic["kCFLocaleLanguageCodeKey"] as? String
    } == "ja"

}