package ru.vladrus13.html.body.createdb

import kotlinx.html.h3
import ru.vladrus13.html.bean.hugeDividedText
import ru.vladrus13.html.bean.relationsContainer
import ru.vladrus13.html.utils.HTMLProjectFile
import ru.vladrus13.html.utils.NestedHTMLFile
import ru.vladrus13.model.sql.SQLTable

object CreateDB : NestedHTMLFile(
    HTMLProjectFile(
        name = "createdb",
        htmlTitle = "Создание DB",
        body = {
            for (relation in relationsContainer) {
                h3 {
                    relation.name
                }
                hugeDividedText(SQLTable.toSQL(relation).toString())
            }
        }
    )
)