package com.example.a30daysapp.ui.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.a30daysapp.R
import com.example.a30daysapp.ui.theme.*

data class ThemeStyle(
    val backgroundColor: Color,
    val fontColor: Color,
    val fontFamily: FontFamily
)

fun getThemeStyle(themeRes: Int): ThemeStyle {
    return when (themeRes) {
        R.string.theme_monday -> ThemeStyle(
            MindBendingColor,
            Color.White,
            FontFamily(Font(R.font.ibmplexsans_regular))
        )
        R.string.theme_tuesday -> ThemeStyle(
            TarantinoColor,
            Color.Black,
            FontFamily(Font(R.font.bebasneue_regular))
        )
        R.string.theme_wednesday -> ThemeStyle(
            WholesomeColor,
            Color.Black,
            FontFamily(Font(R.font.anton_regular))
        )
        R.string.theme_thursday -> ThemeStyle(
            ThrowbackColor,
            Color.White,
            FontFamily(Font(R.font.nunito_regular))
        )
        R.string.theme_friday -> ThemeStyle(
            FincherColor,
            Color.White,
            FontFamily(Font(R.font.josefinsans_regular))
        )
        R.string.theme_saturday -> ThemeStyle(
            SatiricalColor,
            Color.Black,
            FontFamily(Font(R.font.fredoka_regular))
        )
        R.string.theme_sunday -> ThemeStyle(
            SpookyColor,
            Color.White,
            FontFamily(Font(R.font.creepster_regular))
        )
        else -> ThemeStyle(
            Color.Gray,
            Color.Black,
            FontFamily(Font(R.font.anton_regular))
        )
    }
}