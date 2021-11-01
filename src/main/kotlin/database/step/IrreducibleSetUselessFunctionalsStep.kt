package database.step

import database.bean.Attributes
import database.bean.Functionals
import database.bean.Relation
import database.bean.Result
import database.utils.StringUtils.Companion.getString
import database.utils.StringUtils.Companion.getStringOfCollection

class IrreducibleSetUselessFunctionalsStep : RelationRunStep() {
    override fun run(input: Relation): Result<Relation> {
        val attributes = input.attributes.attributes.toSet()
        val functionals = input.functionals.set.toSet()
        val preResult = Result.PreResult()
        preResult.shortInfoAppendLine("Before ISFD")
        preResult.shortInfoAppendLine(input.functionals.getString())
        val newFunctionalsResult = Functionals(functionals).removeAttributesFromFunctionalsUseless()
        preResult += newFunctionalsResult.preResult
        val newFunctionals = newFunctionalsResult.result.set
        preResult.shortInfoAppendLine("After 3 step - useless functionals")
        preResult.shortInfoAppendLine(
            newFunctionals.getStringOfCollection(
                separator = "\n",
                prefix = "",
                postfix = "",
                transform = { it.getString() })
        )
        return Result(preResult, Relation(input.name, Attributes(attributes), Functionals(newFunctionals.toSet())))
    }

    override val name: String = "Irreducible set functional dependencies - useless functionals"
}