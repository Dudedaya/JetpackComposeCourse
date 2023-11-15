package xyz.dudedayaworks.jetpackcompose.playground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import xyz.dudedayaworks.jetpackcompose.playground.ui.theme.JetpackComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePlaygroundTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val state = remember {
                        mutableStateOf(false)
                    }
                    Log.d("TEST", "Recomposition: ${state.value}")
                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = {
                            Log.d("TEST", "onResult=$it")
                        },
                    )

                    // launcher.launch("image/*")
                    // <- crash java.lang.IllegalStateException: Launcher has not been initialized

//                    SideEffect {
//                        // would be called on each recomposition
//                        launcher.launch("image/*")
//                        // ok!
//                    }

                    LaunchedEffect(key1 = Unit) {
                        // Launched only 1 time (if the key doesn't change, or on every recomposition if the key changes)
                        // inside coroutine scope
                        Log.d("TEST", "LaunchedEffect")
                    }
                    SideEffect {
                        // Launched on every recomposition
                        Log.d("TEST", "SideEffect")
                    }

                    Button(onClick = { state.value = !state.value }) {
                        Text(text = "ChangeState")
                    }

//                    SideEffectTest(number = MyNumber(10))
                }
            }
        }
    }
}