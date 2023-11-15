package xyz.dudedayaworks.jetpackcompose.playground.ui.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    onLoggedIn: () -> Unit,
) {
    val viewModel =
        viewModel<LoginViewModel>(factory = LoginViewModel.factory())
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Some Social Network",
            fontFamily = FontFamily.Cursive,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.height(32.dp))
        val inputEnabled = state == LoginScreenState.NotAuthorized
                || state is LoginScreenState.AuthError
        CredentialInputs(
            enabled = inputEnabled,
            onLogin = { username, password ->
                viewModel.onLogin(username, password)
            },
        )
        when (val currentState = state) {
            is LoginScreenState.AuthError -> {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Login error: ${currentState.errorMessage}",
                    fontSize = 18.sp,
                    color = Color.Red,
                )
            }

            LoginScreenState.Authorized -> {
                onLoggedIn()
            }

            LoginScreenState.Loading -> {
                /* no-op */
            }

            LoginScreenState.NotAuthorized -> {
                /* no-op */
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CredentialInputs(
    enabled: Boolean,
    onLogin: (username: String, password: String) -> Unit,
) {
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                },
                placeholder = {
                    Text(text = "Enter @username", color = MaterialTheme.colorScheme.onSecondary)
                },
                label = {
                    Text(text = "Username", color = MaterialTheme.colorScheme.onSecondary)
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                enabled = enabled,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text(text = "Enter password", color = MaterialTheme.colorScheme.onSecondary)
                },
                label = {
                    Text(text = "Password", color = MaterialTheme.colorScheme.onSecondary)
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                visualTransformation = PasswordVisualTransformation(),
                enabled = enabled,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(
                modifier = Modifier
                    .width(TextFieldDefaults.MinWidth),
                onClick = { onLogin(username, password) },
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                )
            ) {
                Text(
                    text = "Login",
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
        if (!enabled) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}