package com.chobitech.lib.kmm

internal expect fun isLangJa(): Boolean

open class LocalizedString(val enString: String, val jpString: String? = null) {

    companion object {
        fun isLanguageIsJapanese(): Boolean = isLangJa()
    }

    override fun toString(): String {
        return when (isLangJa()) {
            true -> jpString ?: enString
            false -> enString
        }
    }
}
