package ru.vladrus13.tornado.utils

import javafx.scene.Parent
import ru.vladrus13.tornado.Styles
import tornadofx.Fragment
import tornadofx.addClass
import tornadofx.label
import tornadofx.vbox

class ErrorWindow : Fragment("Error") {

    val error: String by param("Error")

    override val root: Parent = vbox {
        addClass(Styles.custom)
        label(error)
    }
}