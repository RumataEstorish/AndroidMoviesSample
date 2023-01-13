plugins {
    kotlin("android")
    id("com.android.application")
    id("org.jmailen.kotlinter") version "3.13.0"
    id("com.google.devtools.ksp") version "1.7.21-1.0.8"
}

android {

    compileSdk = 33

    signingConfigs {
        create("release") {
            storeFile = File(projectDir, "/keystore/release.keystore")
            storePassword = "3NMz8K48"
            keyPassword = "3NMz8K48"
            keyAlias = "moviesample"
        }
    }
    namespace = "com.example.androidmoviessample"

    defaultConfig {
        applicationId = "com.example.androidmoviessample"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {

        getByName("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "API_KEY", "\"c9856d0cb57c3f14bf75bdc6c063b8f3\"")
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", "\"c9856d0cb57c3f14bf75bdc6c063b8f3\"")

            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler_version
    }
}

object Versions {
    const val compose_compiler_version = "1.4.0-alpha02"
    const val compose_version = "1.4.0-alpha03"
    const val compose_activity_version = "1.7.0-alpha02"
    const val compose_lifecycle_version = "2.6.0-alpha03"
    const val compose_navigation_version = "2.5.3"
    const val compose_constraint_version = "1.1.0-alpha05"

    const val koin_version = "3.3.2"
    const val koin_android_compose_version = "3.4.1"
}

kotlinter {
    disabledRules = arrayOf("no-wildcard-imports")
}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:${Versions.compose_activity_version}")
    implementation("androidx.compose.ui:ui:${Versions.compose_version}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}")
    implementation("androidx.compose.material3:material3:1.1.0-alpha04")
    implementation("androidx.navigation:navigation-compose:${Versions.compose_navigation_version}")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.0-1.0.8")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    implementation("io.insert-koin:koin-core:${Versions.koin_version}")
    implementation("io.insert-koin:koin-android:${Versions.koin_version}")
    implementation("io.insert-koin:koin-androidx-compose:${Versions.koin_android_compose_version}")

    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.compose_version}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.compose_version}")
}