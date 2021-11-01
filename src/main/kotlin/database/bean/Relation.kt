package database.bean

import java.nio.file.Path
import kotlin.io.path.readText

class Relation(val name : String, val attributes: Attributes, val functionals: Functionals) {
    fun checkContain() {
        functionals.checkContain(attributes)
    }

    companion object {
        fun read(path: Path, name : String) : Relation {
            val attributes = Attributes.read(path.resolve("$name.attributes").readText())
            val functionals = Functionals.read(path.resolve("$name.relations").readText())
            return Relation(name, attributes, functionals)
        }
    }
}