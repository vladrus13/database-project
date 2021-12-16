package ru.vladrus13.html.bean

import kotlinx.html.*

fun FlowContent.spoiler(
    trigger: FlowContent.() -> Unit,
    spoiler: FlowContent.() -> Unit,
    additionalClasses: String? = null
) =
    this.details(classes = "spoiler") {
        if (additionalClasses != null) {
            classes = classes + additionalClasses
        }
        summary {
            trigger()
        }
        div(classes = "indent") {
            spoiler()
        }
    }