package com.example.facebookcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.facebookcompose.R
import com.example.facebookcompose.R.*

val fbFont= FontFamily(
    listOf(
        Font(font.klavika_bold)
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    h6=TextStyle(
        fontFamily = fbFont,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        color = BrandBlue
    ),


    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)