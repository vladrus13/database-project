package ru.vladrus13.html.bean

import kotlinx.html.FlowContent
import kotlinx.html.br
import kotlinx.html.classes
import kotlinx.html.div

fun FlowContent.hugeText(array: List<String>, additionalTags: String? = null) {
    div(classes = "huge-text") {
        if (additionalTags != null) {
            classes = classes + additionalTags
        }
        for (s in array) {
            if (s.isBlank()) br() else div(classes = "line") { +s }
        }
    }
}

fun FlowContent.hugeDividedText(string: String, additionalTags: String? = null) {
    hugeText(string.split("\n"), additionalTags)
}