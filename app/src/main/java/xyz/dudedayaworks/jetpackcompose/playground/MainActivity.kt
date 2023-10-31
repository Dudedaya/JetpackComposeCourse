package xyz.dudedayaworks.jetpackcompose.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import xyz.dudedayaworks.jetpackcompose.playground.ui.InstagramHeader
import xyz.dudedayaworks.jetpackcompose.playground.ui.theme.JetpackComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePlaygroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface,
                ) {
                    InstagramHeader()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextsExample() {
    Text(
        text = "Hello World!",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontFamily = FontFamily.Cursive,
        textDecoration = TextDecoration.combine(
            listOf(TextDecoration.Underline, TextDecoration.LineThrough)
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun TextsExampleAnnotated() {
    Text(
        buildAnnotatedString {
            withStyle(SpanStyle(Color.Red)) {
                append("Hello")
            }
            withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                append(" ")
            }
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                append("World")
            }
            append("!")
        }
    )
}