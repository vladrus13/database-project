package app

import com.google.gson.Gson
import model.ProjectModel
import tornadofx.App
import java.nio.file.Files

class ProjectApp : App(ProjectView::class, Styles::class) {

    val model: ProjectModel by inject()

    override fun stop() {
        if (model.file != null) {
            Files.newBufferedWriter(model.file!!).use {
                val gson = Gson()
                it.append(gson.toJson(model.container))
            }
        }
    }
}