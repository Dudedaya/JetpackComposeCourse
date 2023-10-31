package xyz.dudedayaworks.jetpackcompose.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MultiplicationTable() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        for (i in 1 until 10) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                for (j in 1 until 10) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .background(color = if ((j + i % 2) % 2 == 0) Color.Yellow else Color.White)
                            .border(width = 2.dp, color = Color.Black),
                        contentAlignment = Alignment.Center,
                    ) {
                        val firstRowOrColumn = i == 1 || j == 1
                        val fontSize = if (firstRowOrColumn) 20.sp else 16.sp
                        val fontWeight =
                            if (firstRowOrColumn) FontWeight.Bold else FontWeight.Normal
                        Text(
                            text = (i * j).toString(),
                            fontSize = fontSize,
                            fontWeight = fontWeight,
                        )
                    }
                }
            }
        }
    }
}