package ru.vladrus13.html.body

import kotlinx.html.a
import kotlinx.html.br
import ru.vladrus13.html.body.createdb.CreateDB
import ru.vladrus13.html.body.createtable.TablesCreate
import ru.vladrus13.html.body.ormpdm.ORMPDM
import ru.vladrus13.html.body.transformation.Transformation
import ru.vladrus13.html.utils.*
import java.nio.file.Path

object MainIndex : NestedHTMLFile(
    HTMLProjectFile(
        name = "index",
        htmlTitle = "Project",
        body = {
            ProjectDescription(this)
            a(href = getStart(Path.of("createtable/createtable.html"))) {
                +"Как создавались таблички"
            }
            br()
            a(href = getStart(Path.of("transformation/transformation.html"))) {
                +"Преобразования"
            }
            br()
            a(href = getStart(Path.of("ormpdm/ormpdm.html"))) {
                +"ORM/PDM"
            }
            br()
            a(href = getStart(Path.of("createdb/createdb.html"))) {
                +"Создание БД"
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
            ),
            Folder(
                name = "transformation",
                fileSystem = listOf(
                    Transformation
                )
            ),
            Folder(
                name = "createdb",
                fileSystem = listOf(
                    CreateDB
                )
            )
        )
    )
)

/*
{

}, hashMapOf(Pair("createtable", TablesCreate))
 */