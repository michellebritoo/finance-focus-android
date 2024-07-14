plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "br.com.michellebrito.financefocus"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.michellebrito.financefocus"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.7.2")
    implementation("com.squareup.retrofit2:converter-gson:2.7.2")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("io.insert-koin:koin-core:2.2.3")
    implementation("io.insert-koin:koin-android:2.2.3")
    implementation("io.insert-koin:koin-androidx-viewmodel:2.2.3")
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.redmadrobot:input-mask-android:7.2.4")
    implementation("io.github.vicmikhailau:MaskedEditText:5.0.2")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("com.github.mancj:SlimChart:0.1.2")
    implementation("com.github.kirich1409:viewbindingpropertydelegate-full:1.5.9")
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.9")
    implementation("com.google.firebase:firebase-messaging:24.0.0")
    implementation ("com.airbnb.android:lottie:6.4.1")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}