package com.sknikod.standapp.uti

import kotlin.jvm.JvmInline

object Const {
    enum class ApiUrl(val url: Url) {
        BACKEND(Url("https://kod.prz.edu.pl/backend/api/")),
        MAIN(Url("https://kod.prz.edu.pl/"))
    }
}

@JvmInline
value class Url(val content: String){
    operator fun plus(path: String): Url {
        return Url(content + path)
    }
}
