enum class ImplementationType(val originalName: String) {
    IMPLEMENTATION("implementation"),
    TEST_IMPLEMENTATION("testImplementation"),
    RUNTIME_ONLY("runtimeOnly")

}

interface Libraries {
    fun dependencies(): List<Pair<String, ImplementationType>>

    object Kotlin : Libraries {
        private const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"

        override fun dependencies() = listOf(
            KOTLIN_REFLECT to ImplementationType.IMPLEMENTATION,
        )
    }

    object Jackson : Libraries {
        private const val MODULE_KOTLIN = "com.fasterxml.jackson.module:jackson-module-kotlin:${DependencyVersions.JACKSON_VERSION}"

        override fun dependencies() = listOf(
            MODULE_KOTLIN to ImplementationType.IMPLEMENTATION
        )
    }

    object SpringBoot : Libraries {
        private const val STARTER_WEBFLUX = "org.springframework.boot:spring-boot-starter-webflux"
        private const val STARTER_VALIDATION = "org.springframework.boot:spring-boot-starter-validation"
        private const val STARTER_R2DBC = "org.springframework.boot:spring-boot-starter-data-r2dbc"
        private const val STARTER_SECURITY = "org.springframework.boot:spring-boot-starter-security"

        override fun dependencies() = listOf(
            STARTER_WEBFLUX to ImplementationType.IMPLEMENTATION,
            STARTER_VALIDATION to ImplementationType.IMPLEMENTATION,
            STARTER_R2DBC to ImplementationType.IMPLEMENTATION,
            STARTER_SECURITY to ImplementationType.IMPLEMENTATION
        )
    }

    object Test : Libraries {
        private const val KOTEST_RUNNER = "io.kotest:kotest-runner-junit5:${DependencyVersions.KOTEST_VERSION}"
        private const val MOCKK = "io.mockk:mockk:${DependencyVersions.MOCKK_VERSION}"

        override fun dependencies() = listOf(
            KOTEST_RUNNER to ImplementationType.TEST_IMPLEMENTATION,
            MOCKK to ImplementationType.TEST_IMPLEMENTATION
        )
    }

    object Database : Libraries {
        private const val JASYNC_SQL = "com.github.jasync-sql:jasync-r2dbc-mysql:${DependencyVersions.JASYNC_VERSION}"

        override fun dependencies() = listOf(
            JASYNC_SQL to ImplementationType.RUNTIME_ONLY
        )
    }

    object Reactor : Libraries {
        private const val REACTOR_EXTENSIONS = "io.projectreactor.kotlin:reactor-kotlin-extensions"
        private const val KOTLINX_REACOR = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor"

        override fun dependencies() = listOf(
            KOTLINX_REACOR to ImplementationType.IMPLEMENTATION,
            REACTOR_EXTENSIONS to ImplementationType.IMPLEMENTATION
        )
    }

    object Jwt : Libraries {
        private const val JWT_API = "io.jsonwebtoken:jjwt-api:${DependencyVersions.JWT_VERSION}"
        private const val JWT_IMPL = "io.jsonwebtoken:jjwt-impl:${DependencyVersions.JWT_VERSION}"
        private const val JWT_JACKSON = "io.jsonwebtoken:jjwt-jackson:${DependencyVersions.JWT_VERSION}"
        override fun dependencies() = listOf(
            JWT_API to ImplementationType.IMPLEMENTATION,
            JWT_IMPL to ImplementationType.RUNTIME_ONLY,
            JWT_JACKSON to ImplementationType.RUNTIME_ONLY
        )
    }
}