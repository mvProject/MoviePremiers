import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    // android ui
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    private const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

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


    private const val roundedImage = "com.makeramen:roundedimageview:${Versions.roundedImage}"
    private const val autoFitText = "me.grantland:autofittextview:${Versions.autoFitText}"

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

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    private const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    private const val coilKt = "io.coil-kt:coil:${Versions.coil}"

    val appLibraries = arrayListOf<String>().apply {
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(activityKtx)
        add(material)
        add(fragmentKtx)
        add(recyclerview)
    }

    val logging = arrayListOf<String>().apply {
        add(timber)
    }

    val roundedImageView = arrayListOf<String>().apply {
        add(roundedImage)
    }

    val autoFitTextView = arrayListOf<String>().apply {
        add(autoFitText)
    }

    val annotation = arrayListOf<String>().apply {
        add(annotationX)
    }

    val coil = arrayListOf<String>().apply {
        add(coilKt)
    }

    val lifecycleKtx = arrayListOf<String>().apply {
        add(lifecycleLiveDataKtx)
        add(lifecycleViewModelKtx)
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
}

fun DependencyHandler.implementationHilt() {
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.implementationRoom() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}