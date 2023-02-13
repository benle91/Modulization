package com.android.modulization.data.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 *Created by Hien on 2/12/2023.
 */
class PrefRepository constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        fun getDefaultSharedPreferences(context: Context): SharedPreferences {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
            return EncryptedSharedPreferences.create(
                context,
                "secret_shared_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }

    fun getMasterKey(context: Context) = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    fun clear() {
        sharedPreferences.edit().apply {
            putString(PreferenceKey.ACCESS_TOKEN, "")
            apply()
        }
    }

    fun putString(@PreferenceKey key: String, value: String?) {
        sharedPreferences.edit().apply {
            putString(key, value)
            apply()
        }
    }

    fun getString(@PreferenceKey key: String) = sharedPreferences.getString(key, "")

}

