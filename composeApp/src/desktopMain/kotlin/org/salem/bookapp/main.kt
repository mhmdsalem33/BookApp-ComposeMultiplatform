package org.salem.bookapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.salem.bookapp.app.App
import org.salem.bookapp.setupDI.initKoin


fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CMP-Bookpedia",
        ) {
            App()
        }
    }
}