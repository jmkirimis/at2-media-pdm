package com.fatec.atividade2_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fatec.atividade2_mobile.ui.components.FormCalcularMedia
import com.fatec.atividade2_mobile.ui.theme.Atividade2mobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Atividade2mobileTheme {
                FormCalcularMedia()
            }
        }
    }
}