package ru.newuserkk.volunteers.presentation.common

import android.view.View
import android.widget.ViewSwitcher

fun ViewSwitcher.switchTo(view: View) {
    if (currentView != view) {
        showNext()
    }
}