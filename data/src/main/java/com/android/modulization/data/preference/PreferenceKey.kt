package com.android.modulization.data.preference

import androidx.annotation.StringDef

/**
 *Created by Hien on 2/12/2023.
 */
@Retention(AnnotationRetention.SOURCE)
@StringDef(
    PreferenceKey.ACCESS_TOKEN,
    PreferenceKey.AUTH_REMEMBER_EMAIL,
    PreferenceKey.ACCOUNT_EMAIL,
    PreferenceKey.DISPLAY_NAME
)
annotation class PreferenceKey {
    companion object {
        const val ACCESS_TOKEN = "key_access_token"
        const val AUTH_REMEMBER_EMAIL = "key_auth_remember_email"
        const val ACCOUNT_EMAIL = "key_account_email"
        const val DISPLAY_NAME = "key_display_name"
        const val TOKEN_EXPIRED_AT = "key_display_name"
    }
}