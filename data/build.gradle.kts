plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Project.javaVersion
        targetCompatibility = Project.javaVersion
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    testImplementation(Dependency.Test.junit)
    testImplementation(Dependency.Test.mockito)
    androidTestImplementation(Dependency.Test.androidJunit)
    testImplementation(Dependency.Test.mockitoKotlin)
    testImplementation(Dependency.Test.mockitoInline)

    implementation(Dependency.Coroutine.core)

    implementation(Dependency.Network.retrofit)
    implementation(Dependency.Network.gsonConverter)
    implementation(Dependency.Network.okhttp)

    implementation(Dependency.LocalStorage.sharedPreference)

    implementation(Dependency.DI.inject)
}