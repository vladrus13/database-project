package database.bean

import com.google.gson.annotations.SerializedName
import database.closure.Closure.Companion.getClosureAttributes

data class Functionals(@SerializedName("functionals") val set: Set<Functional>) {
    companion object {
        fun read(s: String): Functionals {
            return Functionals(s.split("\n").filter { it.isNotBlank() }.map {
                Functional.read(it)
            }.toSet())
        }
    }

    operator fun iterator(): Iterator<Functional> {
        return set.iterator()
    }

    fun checkContain(attributes: Attributes) {
        set.forEach {
            it.checkContains(attributes)
        }
    }

    fun removeAttributesFromFunctionalsUseless(): Result<Functionals> {
        val preResult = Result.PreResult()
        val functionals = this.set.toMutableSet()
        var isNewFunctionals = true
        while (isNewFunctionals) {
            isNewFunctionals = false
            val newFunctionals: MutableList<Functional> = mutableListOf()
            val toRemoveFunctionals: MutableList<Functional> = mutableListOf()
            functionals.forEach {
                do {
                    val newResult = it.removeUseless(functionals.toMutableList().apply { remove(it) })
                    val new = newResult.result
                    if (new != null) {
                        preResult += newResult.preResult
                        newFunctionals.add(new)
                        toRemoveFunctionals.add(it)
                        isNewFunctionals = true
                    }
                } while (new != null)
            }
            functionals.addAll(newFunctionals)
            functionals.removeAll(toRemoveFunctionals)
        }
        return Result(preResult, Functionals(functionals))
    }

    fun removeFunctionalsFromFunctionals(): Result<Functionals> {
        var isNewFunctionals = true
        val functionals = this.set.toMutableSet()
        val preResult = Result.PreResult()
        while (isNewFunctionals) {
            isNewFunctionals = false
            for (it in functionals) {
                if (Relation(
                        "",
                        Attributes(it.from),
                        Functionals(functionals.toMutableSet().apply { remove(it) })
                    ).getClosureAttributes().result.attributes.containsAll(it.to)
                ) {
                    preResult.infoAppendLine("Remove rule: $it")
                    functionals.remove(it)
                    isNewFunctionals = true
                    break
                }
            }
        }
        return Result(preResult, Functionals(functionals))
    }
}