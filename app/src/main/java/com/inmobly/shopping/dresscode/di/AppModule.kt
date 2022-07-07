package com.inmobly.shopping.dresscode.di

import android.app.Application
import com.inmobly.shopping.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    @Named("current_language")
    fun provideCurrentLanguage(context: Application): String {
        return context.getString(com.inmobly.common_ui.R.string.language)
    }

    @Singleton
    @Provides
    @Named("DB_NAME")
    fun provideDBName() :String {
        return  if(BuildConfig.FLAVOR=="dresscode") "dresscode_products.db" else "midnightfashion_products.db"
    }

    @Singleton
    @Provides
    @Named("SHARED_PREFS")
    fun provideSharedPrefsName() :String {
        return  if(BuildConfig.FLAVOR=="dresscode") "com.inmobly.shopping.dresscode.pref_user" else "com.inmobly.shopping.midnightfashion.pref_user"
    }
}

