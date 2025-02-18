package com.example.notas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RegisterScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val rePassword = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    var languageSwitch by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .size(150.dp) // Ajusta el tamaño del logo
                .align(Alignment.CenterHorizontally), // Alineación horizontal
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.verlogo),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campos de entrada
        CustomOutlinedTextField("Name", name.value, { name.value = it }, Icons.Default.Person, false)
        Spacer(modifier = Modifier.height(16.dp))

        CustomOutlinedTextField("Email", email.value, { email.value = it }, Icons.Default.Email, false)
        Spacer(modifier = Modifier.height(16.dp))

        CustomOutlinedTextField("Password", password.value, { password.value = it }, Icons.Default.Lock, true)
        Spacer(modifier = Modifier.height(16.dp))

        CustomOutlinedTextField("Re-enter Password", rePassword.value, { rePassword.value = it }, Icons.Default.Lock, true)
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de registro
        Button(
            onClick = { /* Lógica de registro */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Create Account")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto para ir al login
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Already have an account?")
            TextButton(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cambio de idioma
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Change Language")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = languageSwitch,
                onCheckedChange = {
                    languageSwitch = it
                    // Implementar cambio de idioma
                }
            )
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    isPassword: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text
        )
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}