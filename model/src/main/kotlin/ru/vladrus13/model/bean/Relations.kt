package ru.vladrus13.model.bean

import com.google.gson.annotations.SerializedName
import java.nio.file.Path

data class Relations(
    @SerializedName("relations") val relations: MutableSet<Relation>,
    @SerializedName("main") val main: Relation?
) {
    companion object {
        fun read(path: Path): Relations {
            return Relations(
                path.toFile()
                    .listFiles { _, name -> (name.endsWith(".attributes") or name.endsWith(".relations")) }!!
                    .map { it.nameWithoutExtension }
                    .filter { it != "main" }
                    .toSet()
                    .map {
                        Relation.read(path, it)
                    }.toMutableSet(),
                Relation.read(path, "main")
            )
        }
    }

    fun checkContains() {
        relations.forEach {
            it.checkContain()
        }
    }

    operator fun iterator(): Iterator<Relation> {
        return relations.iterator()
    }
}