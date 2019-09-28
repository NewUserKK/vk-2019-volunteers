package ru.wa285.volunteers.presentation.common

import android.os.Bundle

interface NavigationResultListener {
    fun onNavigationResult(action: String, result: Bundle)
}