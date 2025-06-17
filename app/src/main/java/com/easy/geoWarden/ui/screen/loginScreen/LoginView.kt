package com.easy.geoWarden.ui.screen.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easy.geoWarden.data.repository.AuthRepository
import com.easy.geoWarden.data.user.UserState
import com.easy.geoWarden.ui.Theme.theme.GeoWardenTheme
import com.easy.geoWarden.ui.Theme.theme.GradientColorLightBlueToDarkBlue2
import com.easy.geoWarden.ui.Theme.theme.GradientColorLightBlueToDarkBlue3


@Composable
    fun LoginView(onNavigate:() -> Unit) {
    val viewModel: LoginViewModel = LoginViewModel(
        authRepository = AuthRepository()
    )
    val authState = viewModel.userState.collectAsStateWithLifecycle()
    var texto by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    GeoWardenTheme {


        Box(
            modifier = Modifier.fillMaxSize(1f)
                .background(brush = GradientColorLightBlueToDarkBlue3),
            contentAlignment = Alignment.Center
        ) {


            Box(
                modifier = Modifier
                    .height(310.dp)
                    .fillMaxWidth(1f)
                    .background(brush = GradientColorLightBlueToDarkBlue2)
                    .padding(15.dp, 15.dp, 15.dp, 15.dp), contentAlignment = Alignment.Center
            )
            {
                Column {
                    TextField(
                        value = texto,
                        onValueChange = { novoTexto ->
                            texto = novoTexto
                        },

                        label = { Text("Digite seu Email") },
                        placeholder = { Text("Ex: Email") },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.AccountCircle,
                                contentDescription = "Ícone de usuário"
                            )
                        },
                        trailingIcon = {
                            if (texto.isNotEmpty()) {
                                IconButton(onClick = { texto = "" }) {
                                    Icon(Icons.Filled.Clear, contentDescription = "Ícone de limpar")
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(),
                        singleLine = true,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    TextField(
                        value = passwd,
                        onValueChange = { novoPasswd ->
                            passwd = novoPasswd
                        },

                        label = { Text("Digite sua Senha") },
                        placeholder = { Text("Ex: 123456") },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Lock,
                                contentDescription = "Ícone de senha"
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    imageVector = if (isPasswordVisible) Icons.Filled.Build else Icons.Filled.Lock,
                                    contentDescription = if (isPasswordVisible) "Ocultar senha" else "Mostrar senha"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(),
                        singleLine = true,
                        modifier = Modifier.padding(vertical = 8.dp),
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()


                        )
                    Box(Modifier.padding(95.dp, 0.dp)) {
                        Button(onClick = { viewModel.Login(texto,passwd)}) {
                            // Exiba o estado de carregamento se necessário
                            if (authState.value == UserState.Loading) {
                                Text(text = "Carregando...")
                            } else if (authState.value is UserState.Error) {
                                Text(text = "Erro ao fazer login")
                            } else if (authState.value is UserState.LoggedIn) {
                                Text(text = "Login bem-sucedido")
                            }
                             else {
                                Text(text = "Login")
                            }
                        }
                    }
                    //adicionar se houver erro o fundo altera a cor tambem

                }
            }
        }

    }
}
@Preview(showBackground = true)
        @Composable
    fun  PreviewLogin() {
        LoginView(
            onNavigate = {}
        )}

