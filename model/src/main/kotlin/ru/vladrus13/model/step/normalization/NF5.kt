package ru.vladrus13.model.step.normalization

import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.Result
import ru.vladrus13.model.key.Keys.Companion.getKeys
import ru.vladrus13.model.step.RelationRunStep
import ru.vladrus13.model.utils.pathToResources

class NF5 : RelationRunStep() {

    companion object {
        val pathToProofs5 = pathToResources.resolve("input").resolve("proofs").resolve("5")
    }

    override fun run(input: Relation): Result<Relation> {
        val preResult = Result.PreResult()
        val keys = input.getKeys().result
        preResult.infoAppendLine("Ключи:")
        preResult.infoAppendLine(keys.toString())
        keys.forEach {
            if (it.size != 1) {
                val files = pathToProofs5.toFile().listFiles { file ->
                    file.name == input.name + ".proof"
                }
                if (files.size == 1) {
                    preResult.shortInfoAppendLine(files[0].readText())
                    return Result(preResult, input)
                }
                if (files.size > 1) {
                    throw IllegalStateException("Something wrong. I can feel it")
                }
                throw IllegalStateException("Relation ${input.name} with not needed key")
            }
        }
        preResult.shortInfoAppendLine("Все ключи простые. По теореме Дейта-Фейгина 1 мы находимся в 5НФ")
        return Result(preResult, input)
    }

    override val name: String = "Normal form 5"
}