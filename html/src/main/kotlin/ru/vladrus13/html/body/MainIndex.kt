package ru.vladrus13.html.body

import kotlinx.html.a
import ru.vladrus13.html.body.createtable.TablesCreate
import ru.vladrus13.html.utils.getStart
import java.nio.file.Path

object MainIndex : ProjectMark("index", "Project", {
    ProjectDescription(this)
    a(href = getStart(Path.of("createtable/createtable.html")).toString()) {
        +"Как создавались таблички"
    }
}, hashMapOf(Pair("createtable", TablesCreate)))