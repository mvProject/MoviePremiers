import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    // android ui
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    private const val material = "com.google.android.material:material:${Versions.material}"

    // test libs
    private const val junit = "junit:junit:${Versions.junit}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    // network
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val gsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"
    private const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    private const val gson = "com.google.code.gson:gson:${Versions.gson}"

    // annotation
    private const val annotationX = "androidx.annotation:annotation:${Versions.annotation}"

    // lifecycle
    private const val lifecycleLiveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    private const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    // coroutine
    private const val coroutineCoreKtx =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCoreKtx}"
    private const val coroutineAndroidKtx =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"

    // DI
    const val hilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"

    private const val coilKt = "io.coil-kt:coil:${Versions.coil}"

    // Compose
    private const val coilKtCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"
    private const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelCompose}"
    private const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    private const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    private const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

    private const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    val appLibraries = arrayListOf<String>().apply {
        add(coreKtx)
        add(appcompat)
        add(material)
    }

    val appComposeLibraries = arrayListOf<String>().apply {
        add(activityCompose)
        add(composeMaterial)
        add(composeTooling)
    }

    val logging = arrayListOf<String>().apply {
        add(timber)
    }

    val annotation = arrayListOf<String>().apply {
        add(annotationX)
    }

    val coil = arrayListOf<String>().apply {
        add(coilKt)
        add(coilKtCompose)
    }

    val lifecycleKtx = arrayListOf<String>().apply {
        add(lifecycleLiveDataKtx)
        add(lifecycleViewModelKtx)
    }

    val lifecycleCompose = arrayListOf<String>().apply {
        add(viewModelCompose)
    }

    val coroutines = arrayListOf<String>().apply {
        add(coroutineCoreKtx)
        add(coroutineAndroidKtx)
    }

    val network = arrayListOf<String>().apply {
        add(retrofit)
        add(gsonConverter)
        add(loggingInterceptor)
        add(gson)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    val testComposeLibraries = arrayListOf<String>().apply {
        add(composeJunit)
    }
}

fun DependencyHandler.implementationHilt() {
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
}

