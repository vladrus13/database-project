package database.step

import database.bean.Relation
import database.bean.Relations
import database.bean.Result
import kotlin.reflect.KClass

abstract class RelationRunStep : Step<Relations> {

    abstract fun run(input: Relation): Result<Relation>

    override fun run(input: Any): Result<Relations> {
        val preResult = Result.PreResult()
        val relationsInput = input as Relations
        return Result(preResult, Relations(relationsInput.relations.map {
            preResult.shortInfoAppendLine("Take relation: ${it.name}")
            val runner = run(it)
            preResult += runner.preResult
            runner.result
        }.toMutableSet(), relationsInput.main))
    }

    override fun getInputClass(): KClass<*> = Relations::class

    override fun getOutputClass(): KClass<*> = Relations::class
}