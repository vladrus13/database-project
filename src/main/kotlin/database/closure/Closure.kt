package database.closure

import database.bean.*

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

        fun getClosure(relation: Relation): Result<Relation> {
            val attributes = relation.attributes
            val functionals = relation.functionals
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
                Relation(relation.name, Attributes(attributes.attributes.toSet()), Functionals(current))
            )
        }
    }
}