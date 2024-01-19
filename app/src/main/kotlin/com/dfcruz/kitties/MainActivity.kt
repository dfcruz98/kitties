package com.dfcruz.kitties

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dfcruz.kitties.ui.KittiesApp
import com.dfcruz.designsystem.theme.KittiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KittiesTheme {
                KittiesApp()
            }
        }
    }
}