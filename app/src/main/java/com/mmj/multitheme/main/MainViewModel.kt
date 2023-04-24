package com.mmj.multitheme.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mmj.multitheme.app.AppPreferences
import com.mmj.multitheme.ui.theme.AppTheme

class MainViewModel: ViewModel() {

    var stateApp by mutableStateOf(MainState())

    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.ThemeChange -> {
                stateApp = stateApp.copy(theme = event.theme)
            }
            is MainEvent.OpenDialog -> {
                stateApp = stateApp.copy(openDialog = event.openDialog)
            }
        }
    }

}

sealed class MainEvent {
    data class ThemeChange(val theme: AppTheme): MainEvent()
    data class OpenDialog(val openDialog: Boolean): MainEvent()
}

data class MainState(
    val theme: AppTheme = AppPreferences.getTheme(),
    val openDialog: Boolean = false
)