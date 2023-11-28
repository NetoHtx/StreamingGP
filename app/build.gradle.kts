plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.streaminggp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.streaminggp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    // Implementações padrão do Android
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

// Testes
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

// Biblioteca OkHttp para requisições HTTP
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")

// Biblioteca Retrofit para comunicação com APIs REST
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// Biblioteca ConstraintLayout (versão específica para evitar conflitos
    implementation ("androidx.constraintlayout:constraintlayout:2.1.1")

// Biblioteca Picasso para carregar imagens
    implementation ("com.squareup.picasso:picasso:2.8")

    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("org.json:json:20210307")





}