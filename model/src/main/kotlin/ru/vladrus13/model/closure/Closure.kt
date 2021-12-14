package ru.vladrus13.model.closure

import ru.vladrus13.model.bean.*
import ru.vladrus13.model.utils.StringUtils.Companion.getStringOfCollection

class Closure {
    companion object {

        private fun getTrivial(
            attributes: MutableList<String>,
            from: Set<String>,
            to: Set<String>,
            functionals: MutableSet<Functional>
        ) {
            if (attributes.size == 0) {
                functionals.add(Functional(from, to))
            } else {
                val attribute = attributes.removeFirst()
                getTrivial(
                    attributes,
                    from.toMutableSet().apply { add(attribute) },
                    to.toMutableSet().apply { add(attribute) },
                    functionals
                )
                getTrivial(attributes, from.toMutableSet().apply { add(attribute) }, to, functionals)
                getTrivial(attributes, from, to, functionals)
            }
        }

        fun Relation.getClosureFunctional(): Result<Relation> {
            val attributes = this.attributes
            val functionals = this.functionals
            val current = functionals.set.toMutableSet()
            getTrivial(attributes.attributes.toMutableList(), mutableSetOf(), mutableSetOf(), current)
            var isNew = true
            isNew@ while (isNew) {
                isNew = false
                for (functional in functionals) {
                    for (attribute in attributes) {
                        isNew = isNew or current.add(Functional(functional.from + attribute, functional.to + attribute))
                    }
                    if (isNew) continue@isNew
                }
                for (functional1 in functionals) {
                    for (functional2 in functionals) {
                        if (functional1.to == functional2.from) {
                            isNew = isNew or current.add(Functional(functional1.from.toSet(), functional2.to.toSet()))
                            if (isNew) continue@isNew
                        }
                    }
                }
            }
            return Result(
                Result.PreResult(),
                Relation(this.name, Attributes(attributes.attributes.toSet()), Functionals(current))
            )
        }

        fun Relation.getClosureAttributes(): Result<Attributes> {
            val current = this.attributes.attributes.toMutableSet()
            val result = Result.PreResult()
            result.infoAppendLine(current.getStringOfCollection())
            var isNewGet = true
            while (isNewGet) {
                isNewGet = false
                for (functional in this.functionals) {
                    if (current.containsAll(functional.from) && functional.to.any { !current.contains(it) }) {
                        result.fullInfoAppendLine("Использовано правило:")
                            .append(functional.from.joinToString(separator = ", ", prefix = "[", postfix = "]"))
                            .append(" -> ")
                            .append(functional.to.joinToString(separator = ", ", prefix = "[", postfix = "]"))
                            .appendLine()
                        current.addAll(functional.to)
                        result.infoAppendLine(current.getStringOfCollection())
                        isNewGet = true
                        break
                    }
                }
            }
            return Result(result, Attributes(current))
        }
    }
}