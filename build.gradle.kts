plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
    kotlin("jvm") version PluginVersions.JVM_VERSION
}

repositories {
    mavenCentral()
}

group = "me.wangyu"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")
    implementationDependencies(Libraries.SpringBoot)
    implementationDependencies(Libraries.Test)
    implementationDependencies(Libraries.Database)
    implementationDependencies(Libraries.Kotlin)
    implementationDependencies(Libraries.Jackson)
    implementationDependencies(Libraries.Reactor)
    implementationDependencies(Libraries.Jwt)
}