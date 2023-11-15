package xyz.dudedayaworks.jetpackcompose.playground

import android.os.Handler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SideEffectTest(number: MyNumber) {
    Column {
        LazyColumn {
            repeat(5) {
                item {
                    Text(text = "number = ${number.a}")
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Handler().postDelayed({
            number.a = 5
        }, 3000L)
        LazyColumn {
            repeat(5) {
                item {
                    Text(text = "number = ${number.a}")
                }
            }
        }
    }
}

data class MyNumber(var a: Int)