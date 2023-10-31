package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.dudedayaworks.jetpackcompose.playground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun InstagramHeader() {
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onBackground),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(color = Color.Green),
                )
                CounterWithText()
                CounterWithText()
                CounterWithText()
            }
        }
    }
}

@Composable
private fun CounterWithText() {
    Column(
        modifier = Modifier
            .height(64.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(color = Color.Red)
        )
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color = Color.Blue)
        )
    }
}

@Preview
@Composable
private fun PreviewLight() {
    JetpackComposePlaygroundTheme(darkTheme = false) {
        InstagramHeader()
    }
}

@Preview
@Composable
private fun PreviewDark() {
    JetpackComposePlaygroundTheme(darkTheme = true) {
        InstagramHeader()
    }
}