plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {
        applicationId = "com.semicolon.shakeit"
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    composeOptions {
        kotlinCompilerExtensionVersion = Version.jetpackCompose
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }
}

dependencies {
    implementation(project(":di"))
    implementation(project(":domain"))

    implementation(Dependency.coreKtx)
    implementation(Dependency.appcompat)
    implementation(Dependency.androidKtx)
    implementation(Dependency.fragmentKtx)

    implementation(Dependency.UI.material)
    implementation(Dependency.UI.constraintLayout)
    implementation(Dependency.UI.compose)
    implementation(Dependency.UI.composeTooling)
    implementation(Dependency.UI.composePreview)
    implementation(Dependency.UI.composeMaterial)
    implementation(Dependency.UI.composeCompiler)
    implementation(Dependency.UI.activityCompose)
    implementation(Dependency.UI.coilCompose)
    implementation(Dependency.UI.navigationCompose)

    testImplementation(Dependency.Test.junit)
    testImplementation(Dependency.Test.mockito)
    androidTestImplementation(Dependency.Test.androidJunit)
    androidTestImplementation(Dependency.Test.espresso)

    implementation(Dependency.DI.hiltAndroid)
    implementation(Dependency.DI.hiltCompose)
    implementation(Dependency.DI.inject)
    kapt(Dependency.DI.inject)
}