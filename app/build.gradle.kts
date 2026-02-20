import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

android {
    namespace = "com.codaers.f1racehud"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.codaers.f1racehud"
        minSdk = 26
        targetSdk = 36
        versionCode = 4
        versionName = "1.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release")
    }

    buildTypes {
        getByName("release") {
            val storeFilePath = project.findProperty("RELEASE_STORE_FILE") as String?
            val storePass = project.findProperty("RELEASE_STORE_PASSWORD") as String?
            val keyAliasValue = project.findProperty("RELEASE_KEY_ALIAS") as String?
            val keyPass = project.findProperty("RELEASE_KEY_PASSWORD") as String?

            val fileExists = storeFilePath != null && File(storeFilePath).exists()

            if (fileExists &&
                storePass != null &&
                keyAliasValue != null &&
                keyPass != null
            ) {
                signingConfig = signingConfigs.getByName("release").apply {
                    storeFile = file(storeFilePath)
                    storePassword = storePass
                    keyAlias = keyAliasValue
                    keyPassword = keyPass
                }
                println("✅ Release signing enabled")
            } else {
                println("⚠️ Release signing skipped — using debug key")
                signingConfig = signingConfigs.getByName("debug")
            }

            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))

    // Compose UI
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.animation)

    // Material
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material.icons.extended)

    // Navigation
    implementation(libs.navigation.compose)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity.ktx)

    implementation(libs.google.fonts)

    // Material Components
    implementation(libs.material)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}