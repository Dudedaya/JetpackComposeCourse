package xyz.dudedayaworks.jetpackcompose.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Example3()
    }
}

@Composable
private fun Example1() {
    OutlinedButton(onClick = { /*TODO*/ }) {
        Text(text = "Hello World!")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Example2() {
    TextField(
        value = "Value",
        onValueChange = {},
        label = {
            Text(text = "Label")
        }
    )
}

@Composable
private fun Example3() {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = "Are you sure?") },
        text = { Text(text = "Do you want to delete this file?") },
        confirmButton = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "No")
            }
        },
    )
}