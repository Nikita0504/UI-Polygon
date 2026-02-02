package org.dev.uipolygon

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val delegate: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val settings: Settings = SharedPreferencesSettings(delegate)


        setContent {
            App(
                componentContext =  defaultComponentContext(),
                settings = settings
            )
        }
    }
}
