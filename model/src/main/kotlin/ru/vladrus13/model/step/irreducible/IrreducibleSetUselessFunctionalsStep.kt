package ru.vladrus13.model.step.irreducible

import ru.vladrus13.model.bean.Attributes
import ru.vladrus13.model.bean.Functionals
import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.Result
import ru.vladrus13.model.step.RelationRunStep

class IrreducibleSetUselessFunctionalsStep : RelationRunStep() {
    override fun run(input: Relation): Result<Relation> {
        val attributes = input.attributes.attributes.toSet()
        val functionals = input.functionals.set.toSet()
        val preResult = Result.PreResult()
        val newFunctionalsResult = Functionals(functionals).removeAttributesFromFunctionalsUseless()
        preResult += newFunctionalsResult.preResult
        val newFunctionals = newFunctionalsResult.result.set
        return Result(preResult, Relation(input.name, Attributes(attributes), Functionals(newFunctionals.toSet())))
    }

    override val name: String = "Irreducible set functional dependencies - useless functionals"
}