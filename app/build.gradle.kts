plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = "com.mvproject.moviepremiers"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = Config.androidTestInstrumentation
    }

    signingConfigs {
        register("configRelease").configure {
            storeFile = file("../presale.jks")
            storePassword = "iq2umgo9"
            keyAlias = "presale"
            keyPassword = "iq2umgo9"
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            setProperty(
                "archivesBaseName",
                "${rootProject.name}_${project.android.defaultConfig.versionName}"
            )
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("configRelease")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isDebuggable = false
            setProperty(
                "archivesBaseName",
                "${rootProject.name}_${project.android.defaultConfig.versionName}"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    viewBinding {
        android.buildFeatures.viewBinding = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {

    implementation(Dependencies.appLibraries)
    implementation(Dependencies.network)
    implementation(Dependencies.logging)
    implementation(Dependencies.lifecycleKtx)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.coil)

    implementation(Dependencies.roundedImageView)
    implementation(Dependencies.autoFitTextView)

    implementationHilt()

    // Integration with activities
    implementation("androidx.activity:activity-compose:1.4.0")
    // Compose Material Design
    implementation("androidx.compose.material:material:1.1.1")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.1.1")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")

    implementation("io.coil-kt:coil-compose:2.0.0-rc01")

    testImplementation(Dependencies.testLibraries)
    androidTestImplementation(Dependencies.androidTestLibraries)
}
