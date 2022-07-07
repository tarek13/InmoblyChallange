package com.inmobly.shopping.core.domain.util

import com.inmobly.shopping.core.BuildConfig

object AppConstants {
    const val DB_NAME: String="products.db"

    const val SPLASH_TIME_OUT: Long = 2000
    const val PREF_NAME = "com.inmobly.app.pref_user"
    const val BASE_URL = "https://client-tasks.s3.us-west-2.amazonaws.com/challenge/"

    const val FULL_WIDTH_CARD_STYLE="FullWidthCardStyle"
    const val REGULAR_CARD_STYLE="RegularCardStyle"

    const val HOME_SECTION_FULL_WIDTH=1
    const val HOME_SECTION_REGULAR=2

    const val ERROR_CODE_NOT_CONNECTED_TO_INTERNET = 6
    const val ERROR_CODE_TIME_OUT = 7
    const val ERROR_CODE_LOAD_DATA = 8
    const val APP_NAME_DRESS_CODE="dresscode"
    const val APP_NAME_MIDNIGHT_FASHION="midnightfashion"

    const val PRODUCT_ITEM_KEY: String="product"

}