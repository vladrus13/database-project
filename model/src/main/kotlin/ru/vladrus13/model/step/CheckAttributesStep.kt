package ru.vladrus13.model.step

import ru.vladrus13.model.bean.Relations
import ru.vladrus13.model.bean.Result
import kotlin.reflect.KClass

class CheckAttributesStep : Step<Relations> {
    override val name: String = "Check functionals"

    override fun run(input: Any): Result<Relations> {
        (input as Relations).checkContains()
        return Result(Result.PreResult(), input)
    }

    override fun getInputClass(): KClass<*> = Relations::class

    override fun getOutputClass(): KClass<*> = Relations::class
}