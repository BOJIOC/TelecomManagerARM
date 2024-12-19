plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.telecommanager"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.telecommanager"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation ("androidx.test:core:1.5.0")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.3")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation ("junit:junit:4.13.2")
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation("org.mockito:mockito-core:5.14.2")
    androidTestImplementation("org.mockito:mockito-android:5.14.2")
    testImplementation ("org.mockito:mockito-inline:3.11.2")
    testImplementation("org.robolectric:robolectric:4.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0")
    testImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:core:1.4.0")

    // Debugging dependencies
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // SQLite
    implementation ("androidx.sqlite:sqlite:2.4.0") // For SQLite operations
}
