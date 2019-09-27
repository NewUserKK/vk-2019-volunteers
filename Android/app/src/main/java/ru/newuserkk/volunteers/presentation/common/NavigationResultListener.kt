package ru.newuserkk.volunteers.presentation.common

import android.os.Bundle

interface NavigationResultListener {
    fun onNavigationResult(action: String, result: Bundle)
}