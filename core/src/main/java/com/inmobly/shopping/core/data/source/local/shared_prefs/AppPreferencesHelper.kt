package com.inmobly.shopping.core.data.source.local.shared_prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(
    context: Context,

    prefFileName: String,
    val gson: Gson
) : PreferencesHelper {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

   override var lastCacheTime: Long
        get() = sharedPreferences.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = sharedPreferences.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

    companion object {
        private const val PREF_KEY_LAST_CACHE = "last_cache"

    }

}