package ru.vladrus13.html.body

import kotlinx.html.a
import kotlinx.html.br
import ru.vladrus13.html.body.createtable.TablesCreate
import ru.vladrus13.html.body.ormpdm.ORMPDM
import ru.vladrus13.html.utils.*
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
            br()
            a(href = getStart(Path.of("ormpdm/ormpdm.html")).toString()) {
                +"ORM/PDM"
            }

        }
    ), Root(
        listOf(
            Folder(
                name = "createtable",
                fileSystem = listOf(
                    TablesCreate
                )
            ),
            Folder(
                name = "ormpdm",
                fileSystem = listOf(
                    ORMPDM
                )
            )
        )
    )
)

/*
{

}, hashMapOf(Pair("createtable", TablesCreate))
 */