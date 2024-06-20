package com.shockoe.navigationworkshop.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TabContentScreen(title: String, onNext: (() -> Unit)?, onBack: (() -> Unit)?) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, modifier = Modifier.padding(bottom = 10.dp))
            onBack?.let {
                Button(onClick = { it() }) {
                    Text(text = "Click to go back")
                }
            }

            onNext?.let {
                Button(onClick = { it() }) {
                    Text(text = "Click to go next")
                }
            }
        }
    }
}