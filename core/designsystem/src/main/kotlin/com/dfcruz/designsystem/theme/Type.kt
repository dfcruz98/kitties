package com.dfcruz.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dfcruz.designsystem.R

val Poppins = FontFamily(
    fonts = listOf(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
    )
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Poppins,
        fontSize = 57.sp,
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Poppins,
        fontSize = 45.sp,
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Poppins,
        fontSize = 36.sp,
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Poppins,
        fontSize = 32.sp,
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Poppins,
        fontSize = 28.sp,
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Poppins,
        fontSize = 24.sp,
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = Poppins,
        fontSize = 22.sp,
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = Poppins,
        fontSize = 18.sp,
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = Poppins,
        fontSize = 14.sp,
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Poppins,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Poppins,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Poppins,
        fontSize = 12.sp,
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = Poppins,
        fontSize = 14.sp,
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = Poppins,
        fontSize = 12.sp,
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = Poppins,
        fontSize = 10.sp,
    ),
)

