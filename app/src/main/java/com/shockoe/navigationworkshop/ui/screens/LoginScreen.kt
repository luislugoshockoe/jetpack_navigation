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
fun LoginScreen(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Login Screen", modifier = Modifier.padding(bottom = 10.dp))
            Button(onClick = { onLoginClick() }) {
                Text(text = "Click here to Login")
            }
            Button(onClick = { onRegisterClick() }) {
                Text(text = "Click here to Register")
            }
        }
    }
}