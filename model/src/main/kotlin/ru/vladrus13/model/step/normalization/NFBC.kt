package ru.vladrus13.model.step.normalization

import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.Result
import ru.vladrus13.model.key.Keys.Companion.getKeys
import ru.vladrus13.model.step.RelationRunStep

class NFBC : RelationRunStep() {
    override fun run(input: Relation): Result<Relation> {
        val preResult = Result.PreResult()
        val keys = input.getKeys().result
        preResult.infoAppendLine("Ключи:")
        preResult.infoAppendLine(keys.toString())
        for (functional in input.functionals) {
            if (functional.isTrivial()) continue
            keys.any {
                functional.from.containsAll(it)
            }.apply {
                if (!this) throw IllegalStateException()
            }
        }
        return Result(preResult, input)
    }

    override val name: String = "Normal form Boyce–Codd"
}