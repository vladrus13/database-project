package ru.vladrus13.model.step.normalization

import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.Result
import ru.vladrus13.model.key.Keys.Companion.getKeys
import ru.vladrus13.model.step.RelationRunStep
import ru.vladrus13.model.utils.pathToResources

class NF4 : RelationRunStep() {

    companion object {
        val pathToProofs4 = pathToResources.resolve("input").resolve("proofs").resolve("4")
    }

    override fun run(input: Relation): Result<Relation> {
        val preResult = Result.PreResult()
        val keys = input.getKeys().result
        preResult.infoAppendLine("Ключи:")
        preResult.infoAppendLine(keys.toString())
        keys.forEach {
            if (it.size == 1) {
                preResult.shortInfoAppendLine("Существует простой ключ $it. По теореме Дейта-Фейгина 2 мы находимся в 4НФ")
                return Result(preResult, input)
            }
        }
        val files = pathToProofs4.toFile().listFiles { file ->
            file.name == input.name + ".proof"
        }
        if (files.size == 1) {
            preResult.shortInfoAppendLine(files[0].readText())
            return Result(preResult, input)
        }
        if (files.size > 1) {
            throw IllegalStateException("Something wrong. I can feel it")
        }
        throw IllegalStateException("Relation ${input.name} without needed key")
    }

    override val name: String = "Normal form 4"
}