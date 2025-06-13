package com.inrotate.cpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.inrotate.cpc.ui.main.MainView
import com.inrotate.cpc.ui.theme.CPCTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPCTheme (dynamicColor = false) {
                MainView()
            }
        }
    }
}

