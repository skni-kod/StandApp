import Versions.ktorVersion

object Versions {
    const val koin = "3.2.0"
    const val ktorVersion = "2.1.0"
}

object Deps {

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }
    object Ktor {
        const val core = "io.ktor:ktor-client-core:$ktorVersion"
        const val okHttp = "io.ktor:ktor-client-okhttp:$ktorVersion"
        const val darwin = "io.ktor:ktor-client-darwin:$ktorVersion"
    }
}
