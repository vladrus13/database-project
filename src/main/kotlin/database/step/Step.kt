package database.step

import database.bean.Result
import kotlin.reflect.KClass

interface Step<OUTPUT> {
    val name: String

    fun run(input: Any): Result<OUTPUT>

    fun getInputClass(): KClass<*>

    fun getOutputClass(): KClass<*>
}