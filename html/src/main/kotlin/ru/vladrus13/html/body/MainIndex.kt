package ru.vladrus13.html.body

import kotlinx.html.a
import ru.vladrus13.html.body.createtable.TablesCreate
import ru.vladrus13.html.utils.Folder
import ru.vladrus13.html.utils.HTMLProjectFile
import ru.vladrus13.html.utils.NestedHTMLFile
import ru.vladrus13.html.utils.getStart
import java.nio.file.Path

object MainIndex : NestedHTMLFile(
    HTMLProjectFile(
        name = "index",
        htmlTitle = "Project",
        body = {
            ProjectDescription(this)
            a(href = getStart(Path.of("createtable/createtable.html")).toString()) {
                +"Как создавались таблички"
            }
        }
    ), Folder(
        name = "createtable",
        fileSystem = listOf(
            TablesCreate
        )
    )
)

/*
{

}, hashMapOf(Pair("createtable", TablesCreate))
 */