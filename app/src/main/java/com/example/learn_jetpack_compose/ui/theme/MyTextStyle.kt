package com.example.learn_jetpack_compose.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.learn_jetpack_compose.R

class MyTextStyle {
    companion object{
        val textFontBold = FontFamily(
            Font(
                R.font.battambang_bold
            ),
        )

        val textFontLight = FontFamily(
            Font(
                R.font.battambang_light
            ),
        )

        val textFontRegular = FontFamily(
            Font(
                R.font.battambang_regular
            ),
        )




        fun textBold(color: Color,fontSize: Int) : TextStyle{
            return TextStyle(
                color = color,
                fontSize = fontSize.sp,
                fontFamily = textFontBold
            )
        }

        fun textLight(color: Color,fontSize: Int) : TextStyle{
            return TextStyle(
                color = color,
                fontSize = fontSize.sp,
                fontFamily = textFontLight
            )
        }

        fun textRegular(color: Color,fontSize: Int) : TextStyle{
            return TextStyle(
                color = color,
                fontSize = fontSize.sp,
                fontFamily = textFontRegular
            )
        }



    }
}