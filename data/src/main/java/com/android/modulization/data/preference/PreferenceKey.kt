package com.android.modulization.data.preference

import androidx.annotation.StringDef

/**
 *Created by Hien on 2/12/2023.
 */
@Retention(AnnotationRetention.SOURCE)
@StringDef(
    PreferenceKey.ACCESS_TOKEN
)
annotation class PreferenceKey {
    companion object {
        const val ACCESS_TOKEN = "key_access_token"
    }
}