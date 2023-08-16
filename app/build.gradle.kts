plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
}

android {
    namespace = AppConfig.applicationId
    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = 23
        targetSdk = 33
        compileSdk = 33
        versionCode = 1
        versionName = AppConfig.versionName

        testInstrumentationRunner = "AppConfig.testInstrumentationRunner"
    }
    buildFeatures {
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
}

dependencies {
    implementation("io.github.FNCYchain:fncy-wallet-android-sdk:0.1.0")
//    implementation(project(":wallet-sdk"))
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.timber)

    compose()
//    implementation ("androidx.core:core-ktx:1.8.0")
//    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0")
//    testImplementation "junit:junit:4.13.2"
//    androidTestImplementation "androidx.test.ext:junit:1.1.5"
//    androidTestImplementation "androidx.test.espresso:espresso-core:3.5.1"
}