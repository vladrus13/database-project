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
import ru.vladrus13.pictures.imageTable
import ru.vladrus13.pictures.utils.Font
import java.io.File
import java.nio.file.Path
import javax.imageio.ImageIO

fun graphviz() {

    val texts1 = listOf(
        listOf(
            Pair("Name", Font.jetbrainsBold),
            Pair("Type", Font.jetbrainsBold),
            Pair("Attributes", Font.jetbrainsBold)
        ),
        listOf(
            Pair("student_id", Font.jetbrainsBold),
            Pair("id", Font.jetbrainsBold),
            Pair("PK", Font.jetbrainsBold)
        ),
        listOf(Pair("student_name", Font.jetbrainsNormal), Pair("name", Font.jetbrainsNormal)),
        listOf(
            Pair("group_id", Font.jetbrainsItalic),
            Pair("id", Font.jetbrainsItalic),
            Pair("FK1", Font.jetbrainsItalic)
        )
    )
    val image1 = imageTable(texts1)
    val texts2 = listOf(
        listOf(
            Pair("Name", Font.jetbrainsBold), Pair("Type", Font.jetbrainsBold), Pair("Attributes", Font.jetbrainsBold)
        ),
        listOf(
            Pair("group_id", Font.jetbrainsBold),
            Pair("id", Font.jetbrainsBold),
            Pair("PK", Font.jetbrainsBold)
        ),
        listOf(Pair("group_name", Font.jetbrainsNormal), Pair("name", Font.jetbrainsNormal)),
    )
    val image2 = imageTable(texts2)
    ImageIO.write(image1, "jpg", File("temp/image1.png"))
    ImageIO.write(image2, "jpg", File("temp/image2.png"))
    graph(directed = true) {
        "a"[Label.of(""), Shape.BOX, Image.of("temp/image1.png")]
        "b"[Label.of(""), Shape.BOX, Image.of("temp/image2.png")]
        ("a" - "b")
        ("c" - "d")
        ("a" - "d")
        graph[splines(SplineMode.ORTHO)]
    }.toGraphviz().render(Format.PNG).toFile(File("temp/ban.png"))
}

fun main() {
    Project.save(Path.of("site"))
}