package ru.vladrus13

import guru.nidi.graphviz.attribute.GraphAttr.SplineMode
import guru.nidi.graphviz.attribute.GraphAttr.splines
import guru.nidi.graphviz.attribute.Image
import guru.nidi.graphviz.attribute.Label
import guru.nidi.graphviz.attribute.Shape
import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.graph
import guru.nidi.graphviz.toGraphviz
import ru.vladrus13.html.body.Project
import java.io.File
import java.nio.file.Path

fun graphviz() {
    val path = Path.of("site").resolve("createtable").toFile()
    val files = path.listFiles { file ->
        (file.name.contains("erm")) and (file.extension == "png")
    }!!
    val lines = listOf(
        Pair("friendship", "account"),
        Pair("friendship", "account"),
        Pair("world", "account"),
        Pair("map", "world"),
        Pair("impl", "map"),
        Pair("enemy", "unit"),
        Pair("npc", "unit"),
        Pair("hero", "unit"),
        Pair("impl", "enemy"),
        Pair("impl", "npc"),
        Pair("impl", "hero"),
        Pair("inventory", "impl"),
        Pair("inventory", "item"),
        Pair("hero", "account")
    )
    graph(directed = true) {
        for (file in files) {
            file.nameWithoutExtension.split("-")[0][Label.of(""), Shape.BOX, Image.of(file.path.toString())]
        }
        for (line in lines) {
            (line.first - line.second)
        }
        graph[splines(SplineMode.ORTHO)]
    }.toGraphviz().width(1000).render(Format.PNG).toFile(File("temp/ban.png"))
}

fun main() {
    // graphviz()
    Project.save(Path.of("site"))
}