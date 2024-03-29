// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("io.realm.kotlin") version "1.11.0" apply false
}

buildscript {
    val objectboxVersion by extra("3.8.0") // For KTS build scripts

    repositories {
        mavenCentral() // Note: 2.9.0 and older are available on jcenter()
    }

    dependencies {
        // Android Gradle Plugin 4.1.0 or later supported.
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath("io.objectbox:objectbox-gradle-plugin:$objectboxVersion")
        classpath("io.realm:realm-gradle-plugin:2.2.2")
    }
}