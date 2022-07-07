plugins {
    id(GradlePlugins.android)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinParcelize)
    id(GradlePlugins.safeargs)
    id(GradlePlugins.kotlinApt)
    id(GradlePlugins.hilt)
}

android {
    compileSdk = Android.targetSdk
    defaultConfig {
        applicationId = Android.applicationId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName
        multiDexEnabled = true
        testInstrumentationRunner = AndroidJUnitRunner.runner
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


    flavorDimensions.add(ProductFlavors.dimension)

    productFlavors {
        create(ProductFlavors.DRESS_CODE) {
            applicationIdSuffix = ".${ProductFlavors.DRESS_CODE}"
            dimension = ProductFlavors.dimension
            versionName = ProductFlavors.dressCodeVersionName
            versionCode = ProductFlavors.dressCodeVersionCode
        }
        create(ProductFlavors.MIDNIGHT_FASHION) {
            dimension = ProductFlavors.dimension
            applicationIdSuffix = ".${ProductFlavors.MIDNIGHT_FASHION}"
            versionName = ProductFlavors.mednightFachionVersionName
            versionCode = ProductFlavors.mednightFachionVersionCode
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(Modules.Core))
    implementation(project(Modules.CommonUI))

    implementation(KotlinLibs.kotlinStdlib)
    implementation(AndroidSupportLibs.androidxCore)
    implementation(AndroidSupportLibs.appCompat)
    implementation(AndroidSupportLibs.material)
    implementation(AndroidSupportLibs.constraint)
    implementation(AndroidSupportLibs.androidFragmentKtx)
    implementation(AndroidSupportLibs.androidActivityKtx)
    implementation(AndroidSupportLibs.swipeRefreshLayout)
    implementation(AndroidSupportLibs.legacySupport)
    implementation(Shimmer.facebookShimmer)
    // ViewModel
    implementation(LifecycleLibs.viewModelKtx)

    // LiveData
    implementation(LifecycleLibs.liveDataKtx)
    implementation(LifecycleLibs.liveDataRxKtx)

    //hilt
    implementation(DI.hilt)
    kapt(DI.hiltCompiler)

    //CustomWidgets
    implementation(CustomWidgets.aRIndicatorView)
    implementation(CustomWidgets.circleimageview)

    //glide
    implementation(GlideLibs.glide)
    annotationProcessor(GlideLibs.glideCompiler)

    //retrofit
    implementation(RetrofitLibs.retrofit)
    implementation(RetrofitLibs.retrofitGson)
    implementation(OkHttp3Libs.loggingInterceptor)
    implementation(RetrofitLibs.gson)

    // Navigation
    implementation(NavigationLibs.navigationFragment)
    implementation(NavigationLibs.navigationUi)
    implementation(NavigationLibs.navigationDynamicFeaturesFragment)

    // rx
    implementation(ReactiveRx.rxJava)
    implementation(ReactiveRx.rxAndroid)

    //testing
    testImplementation(AndroidTestLibs.junit)
    testImplementation(AndroidTestLibs.archTestCore)
    testImplementation(AndroidTestLibs.robolectric)
    testImplementation(AndroidTestLibs.mockitoCore)
    androidTestImplementation(AndroidTestLibs.androidTestJunit)
    androidTestImplementation(AndroidTestLibs.espressoCore)


}