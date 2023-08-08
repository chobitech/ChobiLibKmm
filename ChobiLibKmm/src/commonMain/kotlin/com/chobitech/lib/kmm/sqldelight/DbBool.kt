package com.chobitech.lib.kmm.sqldelight

//typealias DbBool = Boolean

data class DbBool(val value: Boolean = false) {

    companion object {
        val dbTrue
            get() = DbBool(true)

        val dbFalse
            get() = DbBool(false)
    }

}
