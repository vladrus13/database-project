package ru.vladrus13.model.step

import ru.vladrus13.model.bean.Relations
import ru.vladrus13.model.bean.Result
import java.nio.file.Path
import kotlin.reflect.KClass

class ReadStep : Step<Relations> {
    override val name: String = "Read"

    override fun run(input: Any): Result<Relations> {
        return Result(Result.PreResult(), Relations.read(input as Path))
    }

    override fun getInputClass(): KClass<*> = Path::class

    override fun getOutputClass(): KClass<*> = Relations::class
}