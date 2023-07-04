package com.example.ankitverma.data.local

import android.content.Context
import android.content.SharedPreferences

const val ONBOARDING_STATUS = "Onboarding.screen"
private const val LANGUAGE_PREFERENCE = "com.example.ankitverma.DataStore"

class DataStore(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(LANGUAGE_PREFERENCE, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    internal fun onBoardingDone() {
        editor.putBoolean(ONBOARDING_STATUS, true)
        editor.apply()
    }

    internal fun getOnBoardingStatus(): Boolean = preferences.getBoolean(ONBOARDING_STATUS, false)

}