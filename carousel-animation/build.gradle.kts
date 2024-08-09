import io.grpc.internal.SharedResourceHolder.release

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("maven-publish")
}

android {
    namespace = "com.example.carousel_animation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"

    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(libs.androidx.ui)
    implementation ("androidx.compose.material3:material3:1.2.1")
    implementation(libs.androidx.junit.ktx)
    implementation ("androidx.compose.foundation:foundation:1.4.3")
    implementation(libs.androidx.ui.util.android)
}


/*afterEvaluate {
    publishing {
        publications {
            release(MavenPublication) {
                from components.release
                        groupId = 'com.github.viraj1485'
                artifactId = 'curousel-animation-compose'
                version = '1.0'
            }
        }
    }
}*/

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.viraj1485"
                artifactId = "carousel-animation-compose"
                version = "1.0"
            }
        }
    }
}






