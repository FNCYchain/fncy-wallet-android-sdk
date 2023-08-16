import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    androidLibrary
    kotlinAndroid
    kotlinSerialization
    dokka
    `maven-publish`
    signing
}

android {
    namespace = "com.metaverse.world.wallet.sdk"

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        compileSdk = AppConfig.compileSdkVersion

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures.buildConfig = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.kotlinSerialization)
//    implementation(group = "commons-codec", name = "commons-codec", version = "1.15")
    retrofit()
    okHttp()
    coroutines()
    koin()
    implementation(Dependencies.googlePlayServicesAuth)
    implementation(Dependencies.timber)
    dokkaPlugin(Dependencies.dokka)
    kotest()
    androidTest()
}

val properties = gradleLocalProperties(rootDir)
val signingMap = mapOf(
    "signing.keyId" to properties.getProperty("signing.keyId"),
    "signing.password" to properties.getProperty("signing.password"),
    "signing.key" to properties.getProperty("signing.key")
)

val androidSourceJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs,)
}

android.publishing.singleVariant("release")

group = PUBLISH_GROUP_ID
version = PUBLISH_VERSION

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                if (project.plugins.hasPlugin("com.android.library")) {
                    from(components["release"])
                } else {
                    from(components["java"])
                }
                artifact(androidSourceJar.get())
                groupId = PUBLISH_GROUP_ID
                artifactId = PUBLISH_ARTIFACT_ID
                version = PUBLISH_VERSION
                // Mostly self-explanatory metadata
                pom {
                    name.set("FNCY Wallet SDK")
                    description.set("FNCY Wallet SDK")
                    url.set("https://github.com/FNCYchain/fncy-wallet-android-sdk")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("SanggunPark")
                            name.set("Sanggun Park")
                            email.set("psgxxx@nm-metaworld.com")
                        }
                    }
                    // Version control info
                    scm {
                        connection.set("scm:git:github.com/FNCYchain/fncy-wallet-android-sdk.git")
                        developerConnection.set("scm:git:ssh://github.com/FNCYchain/fncy-wallet-android-sdk.git")
                        url.set("https://github.com/FNCYchain/fncy-wallet-android-sdk/tree/main")
                    }
                }

            }

        }
    }
}

signing {
    useInMemoryPgpKeys(
        signingMap["signing.keyId"],
        signingMap["signing.key"],
        signingMap["signing.password"]
    )
    sign(publishing.publications)
}