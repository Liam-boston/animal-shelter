plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "edu.psu.sweng888.animalshelter"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.psu.sweng888.animalshelter"
        minSdk = 26
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
}

dependencies {
    // import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.3"))

    // import Firebase Authentication
    // https://firebase.google.com/docs/auth/android/start
    implementation("com.google.firebase:firebase-auth")

    // import Cloud Firestore
    // https://firebase.google.com/docs/firestore/
    implementation("com.google.firebase:firebase-firestore")

    // import Splashscreen
    // https://developer.android.com/develop/ui/views/launch/splash-screen#:~:text=Starting%20in%20Android%2012%2C%20the,A%20splash%20screen.
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.google.android.gms:play-services-tasks:18.1.0")
    implementation("com.google.android.gms:play-services-gcm:17.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}