package ru.vladrus13.model.step.normalization

import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.Result
import ru.vladrus13.model.key.Keys.Companion.getKeys
import ru.vladrus13.model.step.RelationRunStep

class NF2 : RelationRunStep() {
    override fun run(input: Relation): Result<Relation> {
        val preResult = Result.PreResult()
        val functionals = input.functionals
        val keys = input.getKeys().result
        preResult.infoAppendLine("Ключи:")
        preResult.infoAppendLine(keys.toString())
        for (functional in functionals) {
            if (functional.isTrivial()) continue
            for (key in keys) {
                if (key.containsAll(functional.from) && !functional.from.containsAll(key)) {
                    throw IllegalStateException()
                    /*
                    val newAttributes = attributes.attributes.toMutableSet().apply { removeAll(functional.to) }
                    val newFunctionals = functionalsCut(newAttributes, functionals)
                    val secondaryAttributes = functional.from + functional.to
                    if (newAttributes.size > 1 && secondaryAttributes.size > 1) {
                        sb.appendLine("Вижу конфликт с зависимостью: ").appendLine(functional.toArrayString())
                        newList.remove(newRatio)
                        newList.add(
                            Pair(
                                Attributes(secondaryAttributes),
                                functionalsCut(secondaryAttributes, functionals)
                            )
                        )
                        newList.add(Pair(Attributes(newAttributes), newFunctionals))
                        sb.appendLine("Разделение:").appendLine(
                            "${Utils.getStringOfList(attributes.attributes)} => ${
                                Utils.getStringOfList(newAttributes)
                            }; ${Utils.getStringOfList(secondaryAttributes)}"
                        )
                        isNew = true
                        break@loop
                    }*/
                }
            }
        }
        return Result(preResult, input)
    }

    override val name: String = "Normal form 2"
}