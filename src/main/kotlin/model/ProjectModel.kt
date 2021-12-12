package model

import tornadofx.ViewModel
import java.nio.file.Path

class ProjectModel : ViewModel() {
    var file: Path? = null

    var container = Container()
}