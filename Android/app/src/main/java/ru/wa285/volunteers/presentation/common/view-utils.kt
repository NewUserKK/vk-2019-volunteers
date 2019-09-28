package ru.wa285.volunteers.presentation.common

import android.view.View
import android.widget.ViewSwitcher

fun ViewSwitcher.switchTo(view: View) {
    if (currentView != view) {
        showNext()
    }
}

fun View.hide(gone: Boolean = true) {
    visibility = if (gone) View.GONE else View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}