import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    nexusPublish
}

buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
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

val properties = gradleLocalProperties(rootDir)
val infoMap = mapOf(
    "ossrhUsername" to properties.getProperty("ossrhUsername"),
    "ossrhPassword" to properties.getProperty("ossrhPassword"),
    "sonatypeStagingProfileId" to properties.getProperty("sonatypeStagingProfileId"),
)

nexusPublishing {
    repositories {
        sonatype {
            stagingProfileId.set(infoMap["sonatypeStagingProfileId"])
            username.set(infoMap["ossrhUsername"])
            password.set(infoMap["ossrhPassword"])
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}