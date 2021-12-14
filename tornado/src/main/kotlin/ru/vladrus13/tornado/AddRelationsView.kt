package ru.vladrus13.tornado

import javafx.scene.Parent
import ru.vladrus13.tornado.model.ProjectModel
import tornadofx.*

class AddRelationsView : View("Table View") {

    val model: ProjectModel by inject()

    override val root: Parent = vbox {
        setPrefSize(1600.0, 1200.0)
        addClass(Styles.custom)
        button("Add relation") {
            action {
                AddRelationView().openWindow()
            }
        }
        button("Finish") {
            action {
                // nothing
            }
        }
    }
}