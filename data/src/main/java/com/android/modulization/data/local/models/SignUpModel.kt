package com.android.modulization.data.local.models

/**
 *Created by Hien on 2/12/2023.
 */
enum class FormFieldState {
    OK,
    MUST_BE_REQUIRE,
    PASSWORD_NOT_ENOUGH_LENGTH,
    PASSWORD_NOT_ENOUGH_STRENGTH,
    EMAIL_NOT_VALID
}