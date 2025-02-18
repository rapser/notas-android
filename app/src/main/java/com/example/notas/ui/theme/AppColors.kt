package com.example.notas.ui.theme

import androidx.compose.ui.graphics.Color

// Definir extensiones de Color para que sean accesibles como Color.kPrimaryColor, Color.textPrimary, etc.
val Color.Companion.kPrimaryColor: Color get() = Color(0xFF5669FF)

// Text Colors
val Color.Companion.textPrimary: Color get() = Color(0xFF5669FF)
val Color.Companion.textBlack: Color get() = Color(0xFF1C1C1C)
val Color.Companion.textGray: Color get() = Color(0xFF7B7B7B)
val Color.Companion.textWhite: Color get() = Color(0xFFF2FEFF)
val Color.Companion.textDarkWhite: Color get() = Color(0xFFF4EBDC)

// Icon Colors
val Color.Companion.lightIcon: Color get() = Color(0xFF7B7B7B)
val Color.Companion.darkIcon: Color get() = Color(0xFFF4EBDC)

// Background Colors
val Color.Companion.lightBackground: Color get() = Color(0xFFF2FEFF)
val Color.Companion.darkBackground: Color get() = Color(0xFF101127)
