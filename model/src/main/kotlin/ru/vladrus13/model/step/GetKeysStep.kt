package ru.vladrus13.model.step

import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.Result
import ru.vladrus13.model.key.Keys.Companion.getKeys
import ru.vladrus13.model.utils.StringUtils.Companion.getStringOfCollection

class GetKeysStep : RelationRunStep() {
    override val name: String = "Get keys"

    override fun run(input: Relation): Result<Relation> {
        val keys = input.getKeys()
        val preResult = Result.PreResult()
        preResult += keys.preResult
        preResult.shortInfoAppendLine(
            keys.result.getStringOfCollection(
                separator = ",\n",
                transform = { it.getStringOfCollection(prefix = "[", postfix = "]") })
        )
        return Result(preResult, input)
    }
}