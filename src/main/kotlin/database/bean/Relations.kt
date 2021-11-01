package database.bean

import java.nio.file.Path

class Relations(val relations: Set<Relation>, val main: Relation) {
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
                    }.toSet(),
                Relation.read(path, "main")
            )
        }
    }

    fun checkContains() {
        relations.forEach {
            it.checkContain()
        }
    }
}