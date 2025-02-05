plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("me.tylerbwong.gradle.metalava") version "0.3.2"
    id("org.jetbrains.dokka")
    id("maven-publish")
}

metalava {
    filename.set("api/current.api")
    reportLintsAsErrors.set(true)
}

android {
    namespace = "weiquanbin.compose.video"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "compose-video"
                version = "1.2.2"
                artifactId = "weiquanbin"
            }
        }
    }
    tasks.named("dokkaHtmlPartial") {
    }
}


dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.media)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.media3)
    implementation(libs.material2)

    debugImplementation(libs.bundles.compose.debugOnly)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidTest)
}
