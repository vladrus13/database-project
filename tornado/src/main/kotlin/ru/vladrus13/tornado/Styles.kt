package ru.vladrus13.tornado

import tornadofx.Stylesheet
import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet() {
    companion object {
        val custom by cssclass()
    }

    init {
        custom {
            fontSize = 30.px
        }
    }
}