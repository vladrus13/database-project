package ru.vladrus13.model.key

import ru.vladrus13.model.bean.Attributes
import ru.vladrus13.model.bean.Functionals
import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.Result
import ru.vladrus13.model.closure.Closure.Companion.getClosureAttributes
import ru.vladrus13.model.utils.StringUtils.Companion.getStringOfAttributes

class Keys {
    companion object {
        private fun getKeys(
            notSeenAttributes: Set<String>,
            attributes: Set<String>,
            cantBan: Set<String>,
            used: MutableMap<Set<String>, Boolean>,
            functionals: Functionals
        ): Result<Set<List<String>>> {
            var flag = false
            val result = Result.PreResult()
            val returned: MutableSet<List<String>> = mutableSetOf()
            val newCantBreak = cantBan.toMutableList()
            notSeenAttributes.forEach {
                if (!cantBan.contains(it)) {
                    val trying = notSeenAttributes.minus(it)
                    result.fullInfoAppendLine("Попытка убрать ключ: $it")
                    if (!used.contains(trying)) {
                        val closure = (Relation("", Attributes(trying), functionals)).getClosureAttributes().result
                        result.fullInfoAppendLine("Получено замыкание: ${closure.getStringOfAttributes(separator = ", ")}")
                        if (closure.attributes.containsAll(attributes) && attributes.containsAll(closure.attributes)) {
                            result.fullInfoAppendLine(
                                "Оно полное! Убираем ключ $it. Подключ: ${
                                    trying.joinToString(
                                        separator = ", "
                                    )
                                }"
                            )
                            val newResult = getKeys(trying, attributes, newCantBreak.toSet(), used, functionals)
                            result += newResult.preResult
                            returned.addAll(newResult.result)
                            flag = true
                        } else {
                            result.fullInfoAppendLine("Оно не полное. Мы не будем убирать этот ключ")
                            newCantBreak.add(it)
                        }
                        used[trying] = flag
                    } else {
                        flag = flag or used[trying]!!
                    }
                }
            }
            if (!flag) {
                returned.add(notSeenAttributes.toList())
                result.fullInfoAppendLine("У нас есть ключ ${notSeenAttributes.joinToString(separator = ", ")}")
            }
            return Result(result, returned)
        }

        fun Relation.getKeys() = getKeys(
            this.attributes.attributes,
            this.attributes.attributes,
            emptySet(),
            mutableMapOf(),
            this.functionals
        )
    }
}