package ru.vladrus13.model.bean

import ru.vladrus13.model.utils.pathToResources
import kotlin.io.path.readText

val types =
    pathToResources.resolve("input").resolve("type-mapping")
        .resolve("type-mapping.txt").readText()
        .let {
            it.split(System.lineSeparator()).map { line ->
                line.split('-').let { pair ->
                    pair[0].trim() to pair[1].trim()
                }
            }
        }.toMap()

class TypedAttributes(val attributes: MutableSet<Pair<String, String>>) {
    companion object {
        fun toTyped(relation: Relation): TypedAttributes =
            TypedAttributes(relation.attributes.attributes.map {
                Pair(it, it.split("_")[0] + "." + it)
            }.map {
                Pair(it.first, types[it.second] ?: throw IllegalStateException("Can't find ${it.second}"))
            }.toMutableSet())
    }

    operator fun iterator(): Iterator<Pair<String, String>> {
        return attributes.sortedBy { it.first }.iterator()
    }
}