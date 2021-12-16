package ru.vladrus13.model.bean

import com.google.gson.annotations.SerializedName
import ru.vladrus13.model.closure.Closure.Companion.getClosureAttributes
import ru.vladrus13.model.utils.StringUtils.Companion.getString

data class Functional(
    @SerializedName("from") var from: Set<String>,
    @SerializedName("to") val to: Set<String>,
    val cause: String? = null
) {
    companion object {
        fun read(functional: String): Functional {
            val splitted = functional.split("->").map { it.trim() }
            check(splitted.size == 2 || splitted.size == 3) { "Can't read from functional! Wrong count of -\">\"" }
            val from = splitted[0].split(",").map { it.trim() }
            val to = splitted[1].split(",").map { it.trim() }
            return Functional(from.toSet(), to.toSet(), if (splitted.size == 3) splitted[2] else null)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Functional
        if (from != other.from) return false
        if (to != other.to) return false

        return true
    }

    override fun hashCode(): Int {
        var result = from.hashCode()
        result = 31 * result + to.hashCode()
        return result
    }

    fun asString(separator: String = " -> ", prefix: String = "", postfix: String = ""): String {
        return prefix + from.joinToString() + separator + to.joinToString() + postfix
    }

    fun checkContains(attributes: Attributes) {
        check(attributes.attributes.containsAll(from)) { "Attributes doesn't contain all from functional" }
        check(attributes.attributes.containsAll(to)) { "Attributes doesn't contain all to functional" }
    }

    fun removeUseless(old: MutableList<Functional>): Result<Functional?> {
        val preResult = Result.PreResult()
        for (trying in from) {
            val newFrom = from.toMutableSet().apply { remove(trying) }
            val newFunctional = Functional(newFrom, to)
            val newFunctionals = Functionals(old.toSet())
            val closureTask = Relation("", Attributes(newFrom), newFunctionals).getClosureAttributes().result.attributes
            if (closureTask.containsAll(to)) {
                preResult.fullInfoAppendLine("Найдено новое бесполезное ${trying}: ${this.getString()}")
                from = from.minus(trying)
                return Result(preResult, newFunctional)
            }
        }
        return Result(preResult, null)
    }
}