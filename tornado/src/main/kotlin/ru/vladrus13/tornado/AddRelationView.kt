package ru.vladrus13.tornado

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import ru.vladrus13.model.bean.Attributes
import ru.vladrus13.model.bean.Functional
import ru.vladrus13.model.bean.Functionals
import ru.vladrus13.model.bean.Relation
import ru.vladrus13.tornado.model.ProjectModel
import ru.vladrus13.tornado.utils.ErrorWindow
import tornadofx.*

class AddRelationView : View("Create table") {

    val model: ProjectModel by inject()

    val attributes = FXCollections.observableArrayList<String>()

    val functionals = FXCollections.observableArrayList<Pair<List<String>, List<String>>>()

    val attributeInput = SimpleStringProperty()

    val functionalsFromInput = SimpleStringProperty()

    val functionalsToInput = SimpleStringProperty()

    val nameInput = SimpleStringProperty()

    override val root = vbox {
        addClass(Styles.custom)

        form {
            fieldset {
                field("Name") {
                    textfield(nameInput)
                }
            }
        }

        listview(attributes)

        form {
            fieldset {
                field("New Attribute") {
                    textfield(attributeInput)
                }
                button("Add attribute") {
                    action {
                        attributes.add(attributeInput.value)
                        attributeInput.value = ""
                    }
                }
            }
        }

        listview(functionals)

        form {
            fieldset {
                field("From") {
                    textfield(functionalsFromInput)
                }
                field("To") {
                    textfield(functionalsToInput)
                }
                button("Add functional") {
                    action {
                        val from = functionalsFromInput.get().split(",").map { it.trim() }
                        val to = functionalsToInput.get().split(",").map { it.trim() }
                        var isBad = false
                        if (from.any { !attributes.contains(it) }) {
                            find<ErrorWindow>(mapOf(ErrorWindow::error to "Doesn't contain all attributes: from")).openWindow()
                            isBad = true
                        }
                        if (!isBad && to.any { !attributes.contains(it) }) {
                            find<ErrorWindow>(mapOf(ErrorWindow::error to "Doesn't contain all attributes: to")).openWindow()
                            isBad = true
                        }
                        if (!isBad) {
                            functionals.add(Pair(from.toList(), to.toList()))
                            functionalsFromInput.value = ""
                            functionalsToInput.value = ""
                        }
                    }
                }
            }
        }

        button("Finish") {
            action {
                model.container.relations.relations.add(
                    Relation(
                        nameInput.get(),
                        Attributes(attributes.toSet()),
                        Functionals(functionals.map { Functional(it.first.toSet(), it.second.toSet()) }.toSet())
                    )
                )
                this@AddRelationView.close()
            }
        }
    }


}