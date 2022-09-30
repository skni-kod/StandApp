package com.sknikod.standapp.android.uti

fun <T> MutableList<T>.changeList(value: (T) -> T): MutableList<T> {
    this.forEachIndexed { i, item ->
        val changedValue = value(item)

        if (value != changedValue) {
            this[i] = changedValue
        }
    }
    return this
}
