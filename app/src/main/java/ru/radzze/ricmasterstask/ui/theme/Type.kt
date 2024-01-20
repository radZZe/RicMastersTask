package ru.radzze.ricmasterstask.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.radzze.ricmasterstask.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val circeFontFamily = FontFamily(
    Font(R.font.circe,FontWeight.Normal),
    Font(R.font.circe_bold,FontWeight.Bold),
    Font(R.font.circe_extrabold,FontWeight.ExtraBold),
    Font(R.font.circe_extralight,FontWeight.ExtraLight),
    Font(R.font.circe_light,FontWeight.Light),
    Font(R.font.circe_thin,FontWeight.Thin)
)