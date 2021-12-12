package app

import com.google.gson.Gson
import javafx.stage.FileChooser
import model.Container
import model.ProjectModel
import tornadofx.*
import java.nio.file.Files

class ProjectView : View("Database project") {

    val model: ProjectModel by inject()

    override val root = hbox {
        setPrefSize(1600.0, 1200.0)
        button("Choose input JSON") {
            addClass(Styles.custom)
            action {
                val files = chooseFile(
                    "Select input JSON",
                    filters = Array(1) { FileChooser.ExtensionFilter("JSON", "*.json") })
                if (files.isNotEmpty()) {
                    model.file = files[0].toPath()
                    if (Files.exists(model.file!!)) {
                        Files.newBufferedReader(model.file!!).use {
                            val gson = Gson()
                            model.container = gson.fromJson(it.readText(), Container::class.java)
                        }
                    }
                    replaceWith<AddRelationsView>()
                }
            }
        }
    }
}