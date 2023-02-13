package com.android.modulization.utils

import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern

/**
 *Created by Hien on 2/12/2023.
 */

fun TextView.setTextAndColor(
    fullString: String,
    subString: String,
    @ColorInt colorSelectedID: Int
) {
    val spannableString = SpannableString(fullString)
    if (fullString.contains(subString)) {
        val subTextIndex = fullString.indexOf(subString)
        spannableString.setSpan(
            ForegroundColorSpan(colorSelectedID),
            subTextIndex,
            subTextIndex + subString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        text = spannableString
    } else {
        setText(fullString, TextView.BufferType.NORMAL)
    }
}

fun TextView.addTextAndColor(
    subString: String,
    @ColorInt colorSelectedID: Int
) {
    val fullString = text.toString().plus(subString)
    val spannableString = SpannableString(fullString)
    val subTextIndex = fullString.indexOf(subString)
    spannableString.setSpan(
        ForegroundColorSpan(colorSelectedID),
        subTextIndex,
        subTextIndex + subString.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = spannableString
}

fun TextView.setHtmlText(sHtml: String) {
    text = Html.fromHtml(sHtml, Html.FROM_HTML_MODE_COMPACT)
}

fun CheckBox.setHtmlText(sHtml: String) {
    text = Html.fromHtml(sHtml, Html.FROM_HTML_MODE_COMPACT)
}

val View.lifecycleScope: LifecycleCoroutineScope?
    get() = findViewTreeLifecycleOwner()?.lifecycleScope

fun View.onSingleClick(action: () -> Unit) =
    setOnClickListener { view ->
        lifecycleScope?.launch {
            view?.isEnabled = false
            action.invoke()
            delay(500)
        }?.invokeOnCompletion {
            view?.isEnabled = true
        }
    }


fun String?.isEmailValid(): Boolean {
    val pattern = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
    return !this.isNullOrEmpty() && pattern.matcher(this).matches()
}

fun String?.isEnoughStrengthPassword(): Boolean {
    val patternContainNumber = Pattern.compile("[0-9]").matcher(this)
    val patternContainLetter = Pattern.compile("[A-Za-z]").matcher(this)
    val patternContainSpecialChar = Pattern.compile("[!@#~$%^&*()\\|\\/\\-_+={}\\[\\]<>?]").matcher(this)
    return patternContainNumber.find() && patternContainLetter.find() && patternContainSpecialChar.find()
}