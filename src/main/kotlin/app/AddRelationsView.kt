package app

import javafx.scene.Parent
import model.ProjectModel
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