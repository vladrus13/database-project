package ru.vladrus13.html.body

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.borderLeft
import java.nio.file.Path
import kotlin.io.path.writeText

object CSS {
    private fun getCSS() =
        CssBuilder().apply {
            rule("*") {
                width = 40.rem
            }
            rule(".main-title") {
                fontFamily = "monospace"
            }
            rule(".huge-text .line") {
                fontFamily = "system-ui"
            }
            rule(".indent") {
                borderLeft(1.rem, BorderStyle.solid, Color.white)
            }
            rule(".relation-table") {
                border(1.px, BorderStyle.solid, Color.black)
            }
            rule(".no-borders") {
                border(0.px, BorderStyle.solid, Color.white)
            }
        }

    operator fun invoke(path: Path) {
        path.writeText(getCSS().toString())
    }
}