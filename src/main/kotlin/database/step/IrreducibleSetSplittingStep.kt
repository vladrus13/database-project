package database.step

import database.bean.*
import database.utils.StringUtils.Companion.getString
import database.utils.StringUtils.Companion.getStringOfCollection

class IrreducibleSetSplittingStep : RelationRunStep() {
    override fun run(input: Relation): Result<Relation> {
        val attributes = input.attributes.attributes.toSet()
        val functionals = input.functionals.set.toSet()
        val preResult = Result.PreResult()
        preResult.shortInfoAppendLine("Before ISFD")
        preResult.shortInfoAppendLine(input.functionals.getString())
        val newFunctionals = functionals.map { functional ->
            val splitted = functional.to.map { to ->
                Functional(functional.from.toSet(), setOf(to))
            }
            if (splitted.size != 1) {
                preResult.infoAppendLine(
                    "Split: ${functional.getString()} to " +
                            splitted.getStringOfCollection(
                                separator = "\n",
                                prefix = "",
                                postfix = "",
                                transform = { it.getString() })
                )
            }
            splitted
        }.flatten()
        preResult.shortInfoAppendLine("After 1 step - splitting")
        preResult.shortInfoAppendLine(
            newFunctionals.getStringOfCollection(
                separator = "\n",
                prefix = "",
                postfix = "",
                transform = { it.getString() })
        )
        return Result(preResult, Relation(input.name, Attributes(attributes), Functionals(newFunctionals.toSet())))
    }

    override val name: String = "Irreducible set functional dependencies - splitting"
}