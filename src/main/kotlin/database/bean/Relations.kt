package database.bean

import java.nio.file.Path

class Relations(val relations: Set<Relation>) {
    companion object {
        fun read(path : Path) : Relations {
            return Relations(
                path.toFile().listFiles()!!.map {
                    Relation.read(it.toPath())
                }.toSet()
            )
        }
    }
}