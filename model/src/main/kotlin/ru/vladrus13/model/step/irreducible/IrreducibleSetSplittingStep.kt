package ru.vladrus13.model.step.irreducible

import ru.vladrus13.model.bean.*
import ru.vladrus13.model.step.RelationRunStep
import ru.vladrus13.model.utils.StringUtils.Companion.getString
import ru.vladrus13.model.utils.StringUtils.Companion.getStringOfCollection

class IrreducibleSetSplittingStep : RelationRunStep() {
    override fun run(input: Relation): Result<Relation> {
        val attributes = input.attributes.attributes.toSet()
        val functionals = input.functionals.set.toSet()
        val preResult = Result.PreResult()
        val newFunctionals = functionals.map { functional ->
            val splitted = functional.to.map { to ->
                Functional(functional.from.toSet(), setOf(to))
            }
            if (splitted.size != 1) {
                preResult.infoAppendLine(
                    "Разделение: ${functional.getString()} to " +
                            splitted.getStringOfCollection(
                                separator = "\n",
                                prefix = "",
                                postfix = "",
                                transform = { it.getString() })
                )
            }
            splitted
        }.flatten()
        return Result(preResult, Relation(input.name, Attributes(attributes), Functionals(newFunctionals.toSet())))
    }

    override val name: String = "Irreducible set functional dependencies - splitting"
}