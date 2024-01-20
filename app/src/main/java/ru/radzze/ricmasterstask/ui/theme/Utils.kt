package ru.radzze.ricmasterstask.ui.theme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CirceText(modifier: Modifier, text: String, size: Int, weight: FontWeight, color: Color) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = size.sp,
        fontWeight = weight,
        fontFamily = circeFontFamily,
        color = color
    )
}