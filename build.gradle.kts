// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization"))
        classpath("org.jetbrains.dokka:dokka-base:$dokkaVersion")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}


tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}