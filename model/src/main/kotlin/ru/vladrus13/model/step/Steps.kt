package ru.vladrus13.model.step

import ru.vladrus13.model.bean.Result
import ru.vladrus13.model.utils.pathToResources
import java.io.BufferedWriter
import java.nio.file.Path
import kotlin.reflect.KClass

class Steps {
    companion object {
        private val list = listOf<Step<*>>(
            ReadStep(),
            CheckAttributesStep(),
            GetKeysStep(),
            IrreducibleSetSplittingStep(),
            IrreducibleSetUselessAttributesStep(),
            IrreducibleSetUselessFunctionalsStep()
        )

        private fun fillString(char: Char, full: Int, from: String): String {
            return "${("" + char).repeat(full - from.length)}$from"
        }

        private fun getPercent(part: Double, s: String): String {
            return "[${"=".repeat((50 * part).toInt())}${"-".repeat(50 - (50 * part).toInt())} ${
                fillString(
                    ' ',
                    3,
                    (100 * part).toInt().toString()
                )
            }%] $s"
        }

        fun runSteps(shortInfo: BufferedWriter, info: BufferedWriter, fullInfo: BufferedWriter) {
            val steps = list.size
            val onePart = 1.0 / steps
            println(getPercent(0.0, "Start process"))
            var preMove: Any = pathToResources.resolve("input")
            var previousClass: KClass<*> = Path::class
            list.forEachIndexed { stepIndex, step ->
                check(step.getInputClass().javaObjectType == previousClass.javaObjectType)
                {
                    "Class on step ${step.name} not equals. " +
                            "Required: ${step.getInputClass().simpleName}, " +
                            "actual: ${previousClass.javaObjectType}"
                }
                val newMove = previousClass.javaObjectType.cast(preMove)
                val preResult = Result.PreResult()
                preResult.shortInfoAppendLine("------------------------ Step: ${step.name} ------------------------")
                val newPreviousClass = step.getOutputClass()
                val newPreMoveResult = step.run(newMove)
                preResult += newPreMoveResult.preResult
                shortInfo.write(preResult.shortInfo.toString())
                shortInfo.newLine()
                info.write(preResult.info.toString())
                info.newLine()
                fullInfo.write(preResult.fullInfo.toString())
                fullInfo.newLine()
                println(getPercent(onePart * (stepIndex + 1), step.name))
                val newPreMove = newPreMoveResult.result
                preMove = newPreMove!!
                previousClass = newPreviousClass
            }
            shortInfo.close()
            info.close()
            fullInfo.close()
        }
    }
}