package ru.vladrus13.model.step.normalization

import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.Result
import ru.vladrus13.model.key.Keys.Companion.getKeys
import ru.vladrus13.model.step.RelationRunStep

class NF3 : RelationRunStep() {
    override fun run(input: Relation): Result<Relation> {
        val preResult = Result.PreResult()
        val attributes = input.attributes
        val functionals = input.functionals
        val keys = input.getKeys().result
        preResult.infoAppendLine("Ключи:")
        preResult.infoAppendLine(keys.toString())
        for (attribute in attributes) {
            var isKey = false
            for (key in keys) {
                isKey = isKey or key.contains(attribute)
            }
            if (!isKey) {
                var isValue = false
                for (functional in functionals) {
                    if (functional.to.contains(attribute)) {
                        isValue = isValue or keys.any {
                            it.containsAll(functional.from)
                        }
                    }
                }
                if (!isValue) {
                    for (functional in functionals) {
                        if (functional.to.contains(attribute)) {
                            throw IllegalStateException()
                            /*
                            sb.appendLine("Аттрибут не зависит от ключей: ").appendLine(attribute)
                            forms.remove(newForm)
                            val newAttributes = attributes.attributes.toMutableSet().apply { remove(attribute) }
                            val newFunctionals = functionalsCut(newAttributes, functionals)
                            forms.add(Pair(Attributes(newAttributes), newFunctionals))
                            val secondaryAttributes = functional.from + attribute
                            forms.add(Pair(Attributes(secondaryAttributes), functionalsCut(secondaryAttributes, functionals)))
                            isNew = true
                            break@loop*/
                        }
                    }
                }
            }
        }
        return Result(preResult, input)
    }

    override val name: String = "Normal form 3"
}