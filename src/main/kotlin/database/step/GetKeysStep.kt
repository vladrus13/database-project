package database.step

import database.bean.Relation
import database.bean.Result
import database.key.Keys.Companion.getKeys
import database.utils.StringUtils.Companion.getStringOfCollection

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