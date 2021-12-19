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
                val consis = if (types[it.second] == null) {
                    val x = types.filter { entry ->
                        it.second.startsWith(entry.key)
                    }.map { entry ->
                        entry.value
                    }
                    if (x.isEmpty()) throw IllegalStateException("Can't find ${it.second}")
                    x[0]
                } else {
                    types[it.second]!!
                }
                Pair(it.first, consis)
            }.toMutableSet())
    }

    operator fun iterator(): Iterator<Pair<String, String>> {
        return attributes.sortedBy { it.first }.iterator()
    }
}