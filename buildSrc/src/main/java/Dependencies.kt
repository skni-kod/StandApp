import Versions.kotlinSerialization
import Versions.ktorVersion

object Versions {
    const val koin = "3.2.2"
    const val ktorVersion = "2.1.0"
    const val kotlinSerialization = "1.3.2"
}

object Deps {

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val navigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin}"
        const val junit5 = "io.insert-koin:koin-test-junit5:${Versions.koin}"
    }
    object Ktor {
        const val core = "io.ktor:ktor-client-core:$ktorVersion"
        const val okHttp = "io.ktor:ktor-client-okhttp:$ktorVersion"
        const val darwin = "io.ktor:ktor-client-darwin:$ktorVersion"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
        const val test ="io.ktor:ktor-client-mock:$ktorVersion"
    }
    object KotlinSerialization {
        const val core = "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerialization"
    }
}
