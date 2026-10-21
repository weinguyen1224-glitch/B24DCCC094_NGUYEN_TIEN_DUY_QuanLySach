plugins {
    id("com.android.application") version "9.0.1"
}

android {
    namespace = "com.library.bookmanager"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.library.bookmanager"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

