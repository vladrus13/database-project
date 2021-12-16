package ru.vladrus13.model.bean

import java.nio.file.Path
import kotlin.io.path.readText

val SqlTypes =
    Path.of("src").resolve("main").resolve("resources").resolve("input").resolve("type-mapping")
        .resolve("types-mapping.txt").readText()
        .let {
            it.split(System.lineSeparator()).map { line ->
                line.split('-').let { pair ->
                    pair[0].trim() to pair[1].trim()
                }
            }
        }.toMap()

class SQLTypesAttributes(val attributes: MutableSet<Pair<String, String>>) {
    companion object {
        fun toTyped(attributes: TypedAttributes): SQLTypesAttributes =
            SQLTypesAttributes(attributes.attributes.map {
                Pair(it.first, SqlTypes[it.second] ?: throw IllegalStateException("Can't find ${it.second}"))
            }.toMutableSet())
    }

    operator fun iterator(): Iterator<Pair<String, String>> {
        return attributes.sortedBy { it.first }.iterator()
    }
}