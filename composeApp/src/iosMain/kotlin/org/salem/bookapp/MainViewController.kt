package org.salem.bookapp

import androidx.compose.ui.window.ComposeUIViewController
import org.salem.bookapp.app.App
import org.salem.bookapp.setupDI.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}