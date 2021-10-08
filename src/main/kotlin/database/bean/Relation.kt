package database.bean

import java.nio.file.Path
import kotlin.io.path.readText

class Relation(val name : String, val attributes: Attributes, val functionals: Functionals) {
    companion object {
        fun read(s : String) : Relation {
            val split = s.split("\n")
            val name = split[0]
            val attributes = Attributes.read(split[1])
            val functionals = Functionals.read(split.drop(2).joinToString("\n"))
            return Relation(name, attributes, functionals)
        }

        fun read(path : Path) : Relation {
            val text = path.readText()
            return read(text)
        }
    }
}