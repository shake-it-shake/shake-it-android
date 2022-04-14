buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependency.GradlePlugin.android)
        classpath(Dependency.GradlePlugin.kotlin)
        classpath(Dependency.GradlePlugin.hilt)
        classpath(Dependency.GradlePlugin.service)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}