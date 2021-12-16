package ru.vladrus13.pictures.utils

import java.awt.Dimension
import java.awt.Font
import java.awt.font.FontRenderContext
import java.awt.geom.AffineTransform

class Font {
    companion object {
        val jetbrainsNormal = Font("Jetbrains Mono", Font.PLAIN, 14)
        val jetbrainsBold = Font("Jetbrains Mono", Font.BOLD, 14)
        val jetbrainsItalic = Font("Jetbrains Mono", Font.ITALIC, 14)
        val jetbrainsBoldItalic = Font("Jetbrains Mono", Font.BOLD or Font.ITALIC, 14)

        fun getLength(pair: Pair<String, Font>): Dimension {
            return getLength(pair.first, pair.second)
        }

        fun getLength(s: String?, font: Font): Dimension {
            if (s == null) return Dimension(0, jetbrainsNormal.size)
            if (s == "") return Dimension(0, jetbrainsNormal.size)
            val frc = FontRenderContext(AffineTransform(), true, true)
            val size = font.getStringBounds(s, frc)
            return Dimension(size.width.toInt(), size.height.toInt())
        }
    }
}