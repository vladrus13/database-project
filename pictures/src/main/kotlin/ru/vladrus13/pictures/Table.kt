package ru.vladrus13.pictures

import ru.vladrus13.pictures.utils.Font
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.max

val listColors = listOf(
    Color(255, 230, 230),
    Color(230, 255, 230),
    Color(230, 230, 255)
)

fun imageTable(list: List<List<Pair<String, java.awt.Font>>>): BufferedImage {
    val n = list.size
    val m = list.stream().mapToInt { it.size }.max().orElseThrow()
    val startsColumn = MutableList(m) {
        var max = 0
        for (i in list.indices) {
            max = max(max, if (list[i].size > it) Font.getLength(list[i][it]).width else 0)
        }
        max + 10
    }
    for (i in 1 until startsColumn.size) {
        startsColumn[i] += startsColumn[i - 1]
    }
    val height = Font.jetbrainsNormal.size
    val bufferedImage = BufferedImage(startsColumn.last(), height * n + 10, BufferedImage.TYPE_INT_RGB)
    val graphics = bufferedImage.graphics
    graphics.color = Color.WHITE
    graphics.fillRect(0, 0, startsColumn.last(), height * (n + 1))
    graphics.color = Color.BLACK
    list.forEachIndexed { rowIndex, _ ->
        if (rowIndex != 0) {
            graphics.color = listColors[rowIndex % listColors.size]
            graphics.fillRect(0, height * rowIndex, startsColumn.last(), height)
            graphics.color = Color.BLACK
        }
    }
    graphics.drawLine(0, height, startsColumn.last(), height)
    list.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, cell ->
            val wid = if (columnIndex == 0) {
                0
            } else {
                startsColumn[columnIndex - 1]
            }
            val hei = height * (rowIndex + 1)
            graphics.font = cell.second
            graphics.drawString(cell.first, wid, hei)
        }
    }
    return bufferedImage
}