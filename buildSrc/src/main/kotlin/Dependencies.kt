import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version

private const val impl = "implementation"
private const val api = "api"
private const val kapt = "kapt"
private const val test = "testImplementation"
private const val androidTest = "androidTestImplementation"

inline val PluginDependenciesSpec.androidApplication: PluginDependencySpec
    get() = id("com.android.application")
inline val PluginDependenciesSpec.androidLibrary: PluginDependencySpec
    get() = id("com.android.library")
inline val PluginDependenciesSpec.kotlinAndroid: PluginDependencySpec
    get() = kotlin("android")
inline val PluginDependenciesSpec.kotlinSerialization: PluginDependencySpec
    get() = kotlin("plugin.serialization")
inline val PluginDependenciesSpec.dokka: PluginDependencySpec
    get() = id("org.jetbrains.dokka") version dokkaVersion

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
    const val appCompat = "androidx.appcompat:appcompat:1.6.1"
    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationJsonVersion"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverterSerialization = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$kotlinXSerializationConverter"
    const val timber = "com.jakewharton.timber:timber:$timberVersion"
    const val googlePlayServicesAuth = "com.google.android.gms:play-services-auth:$gpsaVersion"
    const val koin = "io.insert-koin:koin-android:$koinVersion"
    const val koinTest = "io.insert-koin:koin-test:$koinVersion"
    const val koinAndroidTest = "io.insert-koin:koin-android-test:$koinVersion"

    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion"
    const val dokka = "org.jetbrains.dokka:android-documentation-plugin:$dokkaVersion"

    object Kotest {
        const val runner = "io.kotest:kotest-runner-junit5:$kotestVersion"
        const val assertions = "io.kotest:kotest-assertions-core:$kotestVersion"
        const val property = "io.kotest:kotest-property:$kotestVersion"
        const val koinExt = "io.kotest.extensions:kotest-extensions-koin:1.1.0"
    }

    object Compose {
        const val composeVersion = "2023.03.00"
        const val activityVersion = "1.7.2"
        const val lifecycleVersion = "2.6.1"

        const val bom = "androidx.compose:compose-bom:$composeVersion"
        const val ui = "androidx.compose.ui:ui"
        const val foundation = "androidx.compose.foundation:foundation"
        const val material3 = "androidx.compose.material3:material3"
        const val material3Window = "androidx.compose.material3:material3-window-size-class"
        const val materialIcons = "androidx.compose.material:material-icons-core"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended"

        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val uiViewBinding = "androidx.compose.ui:ui-viewbinding"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val uiTest = "androidx.compose.ui:ui-test-junit4"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"

        const val activity = "androidx.activity:activity-compose:$activityVersion"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion"
    }

    object AndroidTest {
        const val testCoreKtx = "androidx.test:core-ktx:$androidXTestVersion"
        const val testRunner = "androidx.test:runner:$testRunnerVersion"
        const val testRules = "androidx.test:rules:$testRulesVersion"
        const val junitKtx = "androidx.test.ext:junit-ktx:$testJunitVersion"
        const val truth = "androidx.test.ext:truth:$truthVersion"
        const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
    }
}

fun DependencyHandlerScope.androidTest() {
    androidTest(Dependencies.AndroidTest.testCoreKtx)
    androidTest(Dependencies.AndroidTest.testRunner)
    androidTest(Dependencies.AndroidTest.testRules)
    androidTest(Dependencies.AndroidTest.junitKtx)
    androidTest(Dependencies.AndroidTest.truth)
    androidTest(Dependencies.AndroidTest.espressoCore)
}

fun DependencyHandlerScope.compose() {
    impl(platform(Dependencies.Compose.bom))
    impl(Dependencies.Compose.material3)
    impl(Dependencies.Compose.material3Window)
    impl(Dependencies.Compose.materialIcons)
    impl(Dependencies.Compose.materialIconsExtended)
    impl(Dependencies.Compose.foundation)
    impl(Dependencies.Compose.ui)
    impl(Dependencies.Compose.uiTooling)
    // Android Studio Preview support
    impl(Dependencies.Compose.uiToolingPreview)
//    debug(Dependencies.Compose.uiTooling)
    // UI Tests
//    androidTest(Dependencies.Compose.uiTest)
//    debug(Dependencies.Compose.uiTestManifest)

    impl(Dependencies.Compose.uiViewBinding)
//    androidTest(platform(Dependencies.Compose.bom))

    impl(Dependencies.Compose.activity)
    impl(Dependencies.Compose.viewModel)
    impl(Dependencies.Compose.runtime)
}

fun DependencyHandlerScope.coroutines() {
    impl(Dependencies.coroutine)
    test(Dependencies.coroutineTest)
}

fun DependencyHandlerScope.retrofit() {
    impl(Dependencies.retrofit)
    impl(Dependencies.retrofitConverterSerialization)
}

fun DependencyHandlerScope.okHttp() {
    impl(Dependencies.okHttp)
    impl(Dependencies.okHttpInterceptor)
}

fun DependencyHandlerScope.koin() {
    impl(Dependencies.koin)
    test(Dependencies.koinTest)
    androidTest(Dependencies.koinAndroidTest)
}

fun DependencyHandlerScope.kotest() {
    test(Dependencies.Kotest.runner)
    test(Dependencies.Kotest.assertions)
    test(Dependencies.Kotest.property)
    test(Dependencies.Kotest.koinExt)
}