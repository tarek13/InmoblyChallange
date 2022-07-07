
plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroidJetbrains)
    id(GradlePlugins.kotlinApt)
    id(GradlePlugins.hilt)
}

android {
    compileSdk = Android.targetSdk

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk

        testInstrumentationRunner = AndroidJUnitRunner.runner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = BuildTypes.minifyRelease
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(AndroidSupportLibs.androidxCore)
    implementation(AndroidSupportLibs.appCompat)
    implementation(AndroidSupportLibs.material)

    //retrofit
    implementation(RetrofitLibs.retrofit)
    implementation(RetrofitLibs.retrofitGson)
    implementation(RetrofitLibs.retrofitRx)
    implementation(OkHttp3Libs.loggingInterceptor)
    implementation(RetrofitLibs.gson)

    //reactive
    implementation(ReactiveRx.rxJava)
    implementation(ReactiveRx.rxAndroid)

    implementation(DI.hilt)
    implementation(DI.javaxInject)
    kapt(DI.hiltCompiler)

    //room
    implementation(RoomLibs.roomRuntime)
    kapt(RoomLibs.roomCompiler)
    implementation(RoomLibs.roomRxJava)

    //test
    testImplementation(AndroidTestLibs.junit)
    testImplementation(OkHttp3Libs.mockWebServer)
    testImplementation(AndroidTestLibs.robolectric)
    testImplementation(AndroidTestLibs.mockitoCore)


}