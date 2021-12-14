package ru.vladrus13.model.bean

import com.google.gson.annotations.SerializedName
import java.nio.file.Path
import kotlin.io.path.readText

data class Relation(
    @SerializedName("name") val name: String,
    @SerializedName("attributes") val attributes: Attributes,
    @SerializedName("functionals") val functionals: Functionals
) {
    companion object {
        fun read(path: Path, name: String): Relation {
            val attributes = Attributes.read(path.resolve("$name.attributes").readText())
            val functionals = Functionals.read(path.resolve("$name.relations").readText())
            return Relation(name, attributes, functionals)
        }
    }

    fun checkContain() {
        functionals.checkContain(attributes)
    }
}