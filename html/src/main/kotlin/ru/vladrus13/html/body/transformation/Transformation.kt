package ru.vladrus13.html.body.transformation

import kotlinx.html.FlowContent
import ru.vladrus13.html.bean.hugeDividedText
import ru.vladrus13.html.bean.relationTable
import ru.vladrus13.html.bean.relationsContainer
import ru.vladrus13.html.bean.spoiler
import ru.vladrus13.html.utils.HTMLProjectFile
import ru.vladrus13.html.utils.NestedHTMLFile
import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.step.irreducible.IrreducibleSetSplittingStep
import ru.vladrus13.model.step.irreducible.IrreducibleSetUselessAttributesStep
import ru.vladrus13.model.step.irreducible.IrreducibleSetUselessFunctionalsStep
import ru.vladrus13.model.step.normalization.*

fun FlowContent.bodyResult(before: Relation, result: ru.vladrus13.model.bean.Result<*>, after: Relation) {
    spoiler({
        +"До"
    }, {
        relationTable(relation = before, keyGet = false, ermpdm = false)
    })
    spoiler({
        +"Короткий вывод"
    }, {
        hugeDividedText(result.shortInfo.toString())
    })
    spoiler({
        +"Вывод"
    }, {
        hugeDividedText(result.info.toString())
    })
    spoiler({
        +"Длинный вывод"
    }, {
        hugeDividedText(result.fullInfo.toString())
    })
    spoiler({
        +"После"
    }, {
        relationTable(relation = after, keyGet = false, ermpdm = false)
    })
}

fun FlowContent.transformation(relation: Relation) {

    val steps = listOf(
        Pair("Расщепление правых частей", IrreducibleSetSplittingStep()),
        Pair("Удаление ненужных атрибутов", IrreducibleSetUselessAttributesStep()),
        Pair("Удаление ненужных зависимостей", IrreducibleSetUselessFunctionalsStep()),
        Pair("Нормальная форма 1", NF1()),
        Pair("Нормальная форма 2", NF2()),
        Pair("Нормальная форма 3", NF3()),
        Pair("Нормальная форма Бойса-Кодда", NFBC()),
        Pair("Нормальная форма 4", NF4()),
        Pair("Нормальная форма 5", NF5())
    )

    spoiler({
        +relation.name
    }, {
        var current = relation
        for (step in steps) {
            spoiler({
                +step.first
            }, {
                val next = step.second.run(current)
                bodyResult(current, next, next.result)
                current = next.result
            })
        }
    })
}

object Transformation : NestedHTMLFile(
    HTMLProjectFile(
        name = "transformation",
        htmlTitle = "Преобразования",
        body = {
            for (relation in relationsContainer) {
                transformation(relation)
            }
        }
    )
)