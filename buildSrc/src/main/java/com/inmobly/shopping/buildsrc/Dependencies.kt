import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

object Url {
    const val jitPack = "https://jitpack.io"
}

object Android {
    const val minSdk = 21
    const val targetSdk = 32
    const val applicationId = "com.inmobly.shopping"
    const val versionCode = 1
    const val versionName = "1.0"
}

object BuildTypes {
    const val debug = "debug"
    const val release = "release"

    // Release
    const val minifyRelease = false
    const val isShrinkResourcesRelease = false
    val proguardRelease =
        arrayOf(URI.create("proguard-android-optimize.txt"), URI.create("proguard-rules.pro"))

    // Debug
    const val isDebuggable = true
    const val minifyDebug = false
    const val isShrinkResourcesDebug = false
    const val proguardDebug = "proguard-rules.pro"
}

object Modules {
    const val Domain = ":domain"
    const val Data = ":data"
    const val Core = ":core"
    const val CommonUI = ":common-ui"
}

object DataTypes {
    const val string = "String"
}


object ProductFlavors {
    const val dimension = "flavor"

    // DEVELOP
    const val DRESS_CODE = "dresscode"
    var dressCodeVersionName = "1.0"
    var dressCodeVersionCode = 1
    fun devApkName(buildTypeName: String) = "Dev_${getDate()}_${buildTypeName}.apk"

    // BETA
    const val MIDNIGHT_FASHION = "midnightfashion"
    var mednightFachionVersionName = "1.0"
    var mednightFachionVersionCode = 1

    fun betaApkName(buildTypeName: String) = "Testing_${getDate()}_${buildTypeName}.apk"

    // production
    const val production = "production"
    fun productionApkName(buildTypeName: String) = "Prod_${getDate()}_${buildTypeName}.apk"
}

object BuildPlugins {
    private const val androidPluginVersion = "7.2.1"
    private const val googleServicesVersion = "4.3.10"
    private const val firebaseCrashlyticsVersion = "2.8.1"
    private const val firebasePerfPluginVersion = "1.4.1"
    private const val bintrayVersion = "0.3.4"
    private const val gradleUpdateVersion = "0.42.0"

    const val android = "com.android.tools.build:gradle:${androidPluginVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${KotlinLibs.version}"
    const val navigation =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${NavigationLibs.version}"
    const val googleServices = "com.google.gms:google-services:${googleServicesVersion}"
    const val firebaseCrashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${firebaseCrashlyticsVersion}"
    const val firebasePerfPlugin = "com.google.firebase:perf-plugin:$firebasePerfPluginVersion"
    const val bintray = "com.novoda:bintray-release:$bintrayVersion"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${DI.hiltVersion}"
    const val gradleUpdate = "com.github.ben-manes:gradle-versions-plugin:${gradleUpdateVersion}"
}

object GradlePlugins {
    const val android = "com.android.application"
    const val firebasePerf = "com.google.firebase.firebase-perf"
    const val crashlytics = "com.google.firebase.crashlytics"
    const val safeargs = "androidx.navigation.safeargs"

    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinParcelize = "kotlin-parcelize"
    const val kotlinApt = "kotlin-kapt"
    const val javaLib = "java-library"
    const val androidLib = "com.android.library"
    const val navigation = "androidx.navigation.safeargs.kotlin"
    const val googleServices = "com.google.gms.google-services"
    const val hilt = "dagger.hilt.android.plugin"
    const val kotlinAndroidJetbrains = "org.jetbrains.kotlin.android"
    const val kotlinJVM = "org.jetbrains.kotlin.jvm"
    const val gradleUpdate = "com.github.ben-manes.versions"
}

object AndroidJUnitRunner {
    const val runner = "androidx.test.runner.AndroidJUnitRunner"
}

object AndroidTestLibs {
    private const val junitVersion = "4.13.2"
    private const val androidTestVersion = "1.3.0"
    private const val androidTestJunitVersion = "1.1.3"
    private const val archTestCoreVersion = "2.1.0"
    private const val espressoCoreVersion = "3.4.0"
    private const val hamcrestVersion = "1.3"
    private const val robolectricVersion = "4.4"
    private const val mockitoVersionCore = "2.22.0"
    private const val mockitoVersionAll = "1.10.19"
    private const val kotlinTestVersion = "1.3.70"

    // Core
    const val junit = "junit:junit:${junitVersion}"
    const val androidTestCore = "androidx.test:core:${androidTestVersion}"

    // AndroidJUnitRunner and JUnit Rules
    const val androidTestRunner = "androidx.test:runner:${androidTestJunitVersion}"
    const val androidTestRules = "androidx.test:rules:${androidTestVersion}"

    // Assertions
    const val androidTestTruth = "androidx.test.ext:truth:${androidTestVersion}"
    const val androidTestJunit = "androidx.test.ext:junit:${androidTestJunitVersion}"

    // LiveData
    const val archTestCore = "androidx.arch.core:core-testing:${archTestCoreVersion}"

    // Espresso core
    const val espressoCore = "androidx.test.espresso:espresso-core:${espressoCoreVersion}"

    // Hamcrest
    const val hamcrest = "org.hamcrest:hamcrest-all:${hamcrestVersion}"

    // Robolectric
    const val robolectric = "org.robolectric:robolectric:${robolectricVersion}"

    //mockito
    // Mockito for testing
    const val mockitoCore = "org.mockito:mockito-core:${mockitoVersionCore}"
    const val mockitoAll = "org.mockito:mockito-all:${mockitoVersionAll}"

    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${kotlinTestVersion}"


}

object KotlinLibs {
    internal const val version = "1.7.0"

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${version}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${version}"
}

object LifecycleLibs {
    private const val version = "2.4.1"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${version}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${version}"
    const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${version}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${version}"
    const val liveDataRxKtx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${version}"
}

object AndroidSupportLibs {
    private const val androidxCoreVersion = "1.8.0"
    private const val annotationsVersion = "1.3.0"
    private const val appCompatVersion = "1.4.2"
    private const val cardViewVersion = "1.0.0"
    private const val materialVersion = "1.6.1"
    private const val constraintVersion = "2.1.4"
    private const val recyclerViewVersion = "1.2.1"
    private const val swipeRefreshLayoutVersion = "1.1.0"
    private const val viewPager2Version = "1.0.0"
    private const val legacySupportVersion = "1.0.0"
    private const val multidexVersion = "2.0.1"
    private const val androidActivityKtxVersion = "1.4.0"
    private const val androidFragmentKtxVersion = "1.4.1"

    const val androidxCore = "androidx.core:core-ktx:${androidxCoreVersion}"
    const val annotations = "androidx.annotation:annotation:${annotationsVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${appCompatVersion}"
    const val cardView = "androidx.cardview:cardview:${cardViewVersion}"
    const val material = "com.google.android.material:material:${materialVersion}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${constraintVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${recyclerViewVersion}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${swipeRefreshLayoutVersion}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${viewPager2Version}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${legacySupportVersion}"
    const val multidex = "androidx.multidex:multidex:${multidexVersion}"
    const val androidActivityKtx = "androidx.activity:activity-ktx:${androidActivityKtxVersion}"
    const val androidFragmentKtx = "androidx.fragment:fragment-ktx:${androidFragmentKtxVersion}"

}

object DI {
    const val hiltVersion = "2.42"

    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
    const val javaxInject = "javax.inject:javax.inject:1"
}

object DataStore {
    private const val datastoreVersion = "1.0.0"

    const val dataStore = "androidx.datastore:datastore-preferences:$datastoreVersion"
    const val dataStoreRx = "androidx.datastore:datastore-preferences-rxjava2:$datastoreVersion"

}

object PlayServices {
    private const val adsIdentifierVersion = "18.0.1"
    private const val installreferrerVersion = "2.2"
    private const val playServicesAuthVersion = "19.2.0"
    private const val playServicesAuthApiPhoneVersion = "17.5.1"

    const val adsIdentifier =
        "com.google.android.gms:play-services-ads-identifier:${adsIdentifierVersion}"
    const val installreferrer =
        "com.android.installreferrer:installreferrer:${installreferrerVersion}"
    const val playServicesAuth =
        "com.google.android.gms:play-services-auth:$playServicesAuthVersion"
    const val playServicesAuthApiPhone =
        "com.google.android.gms:play-services-auth-api-phone:$playServicesAuthApiPhoneVersion"
}

object Acra {
    private const val acraVersion = "5.8.4"

    const val acra = "ch.acra:acra-mail:$acraVersion"

}


object NavigationLibs {
    internal const val version = "2.4.2"
    internal const val versionAlpha = "2.5.0-alpha01"// fix error when update bumble bee

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${version}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${version}"
    const val navigationDynamicFeaturesFragment =
        "androidx.navigation:navigation-dynamic-features-fragment:$version"
    const val navigationTesting = "androidx.navigation:navigation-testing:$version"

}

object Fonts {
    internal const val version = "1.0.6"
    const val ssp = "com.intuit.ssp:ssp-android:${version}"
    const val sdp = "com.intuit.sdp:sdp-android:${version}"
}

object RoomLibs {
    private const val version = "2.4.2"
    private const val debugDBversion = "1.0.6"

    const val roomRuntime = "androidx.room:room-runtime:${version}"
    const val roomCompiler = "androidx.room:room-compiler:${version}"
    const val roomRxJava = "androidx.room:room-rxjava2:${version}"
    const val roomKtx = "androidx.room:room-ktx:${version}"
    const val roomTesting = "androidx.room:room-testing:$version"
    const val debugDb = "com.amitshekhar.android:debug-db:$debugDBversion"
    const val debugDbEncrypt = "com.amitshekhar.android:debug-db-encrypt:$debugDBversion"
}

object CoroutinesLibs {
    private const val version = "1.4.3"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${version}"
    const val androidCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${version}"
}

object KoinLibs {
    private const val version = "2.2.2"

    const val koinCore = "org.koin:koin-core:${version}"
    const val koinAndroid = "org.koin:koin-android:${version}"
    const val koinAndroidScope = "org.koin:koin-androidx-scope:${version}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${version}"
    const val koinFragment = "org.koin:koin-androidx-fragment:${version}"
    const val koinTest = "org.koin:koin-test:${version}"
}

object FirebaseLibs {
    private const val version = "30.0.2"

    const val firebaseBom = "com.google.firebase:firebase-bom:${version}"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebasePerf = "com.google.firebase:firebase-perf-ktx"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging-ktx"
    const val firebaseInappmessagingDisplay =
        "com.google.firebase:firebase-inappmessaging-display-ktx"
    const val firebaseAppindexing = "com.google.firebase:firebase-appindexing"
}

object Mixpenl {
    private const val version = "6.0.0"
    const val mixpanel = "com.mixpanel.android:mixpanel-android:$version"

}

object ReactiveRx {
    private const val javaVersion = "2.2.21"
    private const val androidVersion = "2.1.1"

    const val rxJava = "io.reactivex.rxjava2:rxjava:$javaVersion"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$androidVersion"
    const val rxStream = "android.arch.lifecycle:reactivestreams:1.1.1"
}

object DeepLinking {
    private const val branchIoVersion = "5.1.4"
    const val branchIo = "io.branch.sdk.android:library:$branchIoVersion"
}

object Events {
    private const val eventBusVersion = "3.3.1"
    const val eventBus = "org.greenrobot:eventbus:$eventBusVersion"
}

object RetrofitLibs {
    private const val version = "2.9.0"

    const val retrofit = "com.squareup.retrofit2:retrofit:${version}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${version}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${version}"
    const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:$version"

    private const val moshiVersion = "1.11.0"
    private const val moshiKotlinVersion = "1.12.0"
    private const val gsonVersion = "2.8.9"

    //    const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiKotlinVersion"
    const val gson = "com.google.code.gson:gson:$gsonVersion"
}

object GlideLibs {
    private const val version = "4.13.2"

    const val glide = "com.github.bumptech.glide:glide:${version}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${version}"

    const val glideOkhttp3 = "com.github.bumptech.glide:okhttp3-integration:$version"
    const val grpcOkhttp3 = "io.grpc:grpc-okhttp:1.46.0"


    //        exclude group: 'glide-parent'
//    }
    private const val glideTransformationsVersion = "3.3.0"

    const val glideTransformations =
        "jp.wasabeef:glide-transformations:$glideTransformationsVersion"


}

object EasyPermissionsLibs {
    private const val version = "3.0.0"

    const val easyPermissions = "pub.devrel:easypermissions:${version}"
}

object TimberLibs {
    private const val version = "5.0.1"

    const val timber = "com.jakewharton.timber:timber:${version}"
}

object ChuckerLibs {
    private const val version = "3.5.2"

    const val debugChucker = "com.github.chuckerteam.chucker:library:${version}"
    const val releaseChucker = "com.github.chuckerteam.chucker:library-no-op:${version}"
}

object LeakCanaryLibs {
    private const val version = "2.6"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${version}"
}

object OkHttp3Libs {
    private const val version = "4.9.3"

    const val okHttp3 = "com.squareup.okhttp3:okhttp:${version}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${version}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${version}"
}

object JunitDataProviderLibs {
    private const val version = "2.6"

    const val junitDataProvider = "com.tngtech.junit.dataprovider:junit4-dataprovider:${version}"
}

object MockKLibs {
    private const val version = "1.11.0"

    const val mockK = "io.mockk:mockk:${version}"
    const val androidMockK = "io.mockk:mockk-android:${version}"
}

object Localizations {
    private const val version = "1.2.11"

    const val localizationLib = "com.akexorcist:localization:$version"
}

object CustomWidgets {
    private const val materialDialogsVersion = "0.9.6.0"
    private const val countDownViewVersion = "2.1.6"
    private const val otpviewPinviewVersion = "2.1.2"
    private const val pageindicatorviewVersion = "1.0.3"
    private const val dotsindicatorVersion = "4.2"
    private const val circleviewVersion = "1.4.0"
    private const val androidSnowfallVersion = "1.2.1"
    private const val materialdrawerVersion = "8.4.5"
    private const val ucropVersion = "2.2.8"
    private const val circleprogressVersion = "1.2.1"
    private const val ExpandableLayoutVersion = "2.9.2"
    private const val circleimageviewVersion = "3.1.0"
    private const val androidyoutubeplayerVersion = "11.0.1"
    private const val materialdatetimepickerVersion = "4.2.3"
    private const val SupportedPickerDialogsVersion = "1.0.1"
    private const val RecyclerviewVersion = "1.2.1"
    private const val aRIndicatorViewVersion = "2.0.0"

    const val materialDialogs = "com.afollestad.material-dialogs:core:$materialDialogsVersion"
    const val materialDialogsCommons =
        "com.afollestad.material-dialogs:commons:$materialDialogsVersion"
    const val countDownView = "com.github.iwgang:countdownview:$countDownViewVersion"
    const val otpviewPinview =
        "com.github.mukeshsolanki:android-otpview-pinview:$otpviewPinviewVersion"
    const val pageindicatorview = "com.romandanylyk:pageindicatorview:$pageindicatorviewVersion"
    const val dotsindicator = "com.tbuonomo:dotsindicator:$dotsindicatorVersion"
    const val circleview = "com.mikhaellopez:circleview:$circleviewVersion"
    const val androidSnowfall = "com.github.jetradarmobile:android-snowfall:$androidSnowfallVersion"
    const val materialdrawer = "com.mikepenz:materialdrawer:$materialdrawerVersion"
    const val ucrop = "com.github.yalantis:ucrop:$ucropVersion"
    const val circleprogress = "com.github.lzyzsd:circleprogress:$circleprogressVersion"
    const val ExpandableLayout = "com.github.cachapa:ExpandableLayout:$ExpandableLayoutVersion"
    const val circleimageview = "de.hdodenhof:circleimageview:$circleimageviewVersion"
    const val androidyoutubeplayer =
        "com.pierfrancescosoffritti.androidyoutubeplayer:core:$androidyoutubeplayerVersion"
    const val materialdatetimepicker =
        "com.wdullaer:materialdatetimepicker:$materialdatetimepickerVersion"
    const val SupportedPickerDialogs =
        "com.github.Ibotta:Supported-Picker-Dialogs:$SupportedPickerDialogsVersion"
    const val Recyclerview =
        "androidx.recyclerview:recyclerview:$RecyclerviewVersion"
    const val tooltip = "com.github.bingoogolapple.BGATransformersTip-Android:library:1.0.8@aar"
    const val aRIndicatorView =
        "com.github.martinstamenkovski:ARIndicatorView:$aRIndicatorViewVersion"

}

object FullImageScreen {
    private const val frescoVersion = "1.10.0"
    private const val frescoimageviewerVersion = "0.5.0"
    private const val frescoProcessorsVersion = "2.0.0"

//    const val fresco = "com.facebook.fresco:fresco:$frescoVersion"
//    const val frescoimageviewer = "com.github.stfalcon:frescoimageviewer:$frescoimageviewerVersion"
//    const val frescoProcessors = "jp.wasabeef:fresco-processors:$frescoProcessorsVersion"
}

object QRReaders {
    private const val zxingAndroidEmbeddedVersion = "4.3.0"
    private const val zxingCoreVersion = "3.5.0"

    const val zxingAndroidEmbedded =
        "com.journeyapps:zxing-android-embedded:$zxingAndroidEmbeddedVersion"
    const val zxingCore = "com.google.zxing:core:$zxingCoreVersion"

}

object Paging3 {
    private const val paging_version = "3.1.1"
    const val pagingRunTime = "androidx.paging:paging-runtime:$paging_version"
    const val pagingRxJava = "androidx.paging:paging-rxjava2:$paging_version"
    const val pagingCommon = "androidx.paging:paging-common:$paging_version"
}

object Intercom {
    private const val intercomVersion = "12.2.2"
    const val intercom = "io.intercom.android:intercom-sdk:${intercomVersion}"
}

object Codec {
    const val codec = "commons-codec:commons-codec:1.15"
}

object Facebook {
    private const val facebookVersion = "13.1.0"
    const val facebookSdk = "com.facebook.android:facebook-android-sdk:${facebookVersion}"
}

object MapStruct {
    private const val mapStructVersion = "1.5.0.RC1"
    const val mapStruct = "org.mapstruct:mapstruct:${mapStructVersion}"
    const val mapStructAnnotation = "org.mapstruct:mapstruct-processor:${mapStructVersion}"
}

object Lottie {
    private const val lottieVersion = "5.1.1"
    const val lottie = "com.airbnb.android:lottie:${lottieVersion}"
}

object JsonApi {
    private const val jsonApiVersion = "0.11"
    const val jsonApiMoshi = "com.github.jasminb:jsonapi-converter:${jsonApiVersion}"
}


object Work {
    private const val workManagerVersion = "2.7.1"
    const val workManager = "androidx.work:work-runtime-ktx:$workManagerVersion"
}

object Shimmer {
    private const val facebookShimmerVersion = "0.5.0"
    const val facebookShimmer = "com.facebook.shimmer:shimmer:$facebookShimmerVersion"
}

object Amplitude {
    private const val amplitudeVersion = "2.36.1"
    const val amplitude = "com.amplitude:android-sdk:$amplitudeVersion"
}

fun getDate(): String {
    val df = SimpleDateFormat("dd-MM-yyyy\$HH!mm!ss", Locale.US)
    return df.format(Date())
}

object DependencyUpdates {

    private val maturityLevels =
        listOf("preview", "alpha", "beta", "m", "cr", "rc") // order is important!

    fun maturityLevel(version: String): Int {
        maturityLevels.forEachIndexed { index, s ->
            if (version.matches(".*[.\\-]$s[.\\-\\d]*".toRegex(RegexOption.IGNORE_CASE))) return index
        }
        return maturityLevels.size
    }
}