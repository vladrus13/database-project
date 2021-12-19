package ru.vladrus13.model.step.normalization

import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.Result
import ru.vladrus13.model.closure.Closure.Companion.getClosureFunctional
import ru.vladrus13.model.step.RelationRunStep

class NF1 : RelationRunStep() {
    override fun run(input: Relation): Result<Relation> {
        val preResult = Result.PreResult()
        preResult.infoAppendLine("К счастью, повторяющихся нет, и они все атомарны")
        return Result(preResult, input.getClosureFunctional().result)
    }

    override val name: String = "Normal form 1"
}