plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroidJetbrains)
    id(GradlePlugins.kotlinApt)
    id(GradlePlugins.hilt)
    id(GradlePlugins.kotlinParcelize)

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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.Core))

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

    // Navigation
    implementation(NavigationLibs.navigationFragment)
    implementation(NavigationLibs.navigationUi)
    implementation(NavigationLibs.navigationDynamicFeaturesFragment)

    //glide
    implementation(GlideLibs.glide)
    annotationProcessor(GlideLibs.glideCompiler)


    //hilt
    implementation(DI.hilt)
    kapt(DI.hiltCompiler)

    //retrofit
    implementation(RetrofitLibs.retrofit)

}