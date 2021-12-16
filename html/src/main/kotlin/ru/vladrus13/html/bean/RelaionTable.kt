package ru.vladrus13.html.bean

import kotlinx.html.*
import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.TypedAttributes
import ru.vladrus13.model.key.Keys.Companion.getKeys

fun FlowContent.relationTable(relation: Relation) {
    val keys = relation.getKeys()
    val typed = TypedAttributes.toTyped(relation)
    table(classes = "relation-table") {
        caption {
            +relation.name
        }
        var fk = 0
        for (attribute in typed) {
            tr {
                td(classes = "no-borders") {
                    +attribute.first
                }
                td(classes = "no-borders") {
                    +attribute.second
                }
                td(classes = "no-borders") {
                    +mutableListOf<String>().apply {
                        keys.result.forEachIndexed { index, key ->
                            if (key.contains(attribute.first)) {
                                this.add("PK$index")
                            }
                        }
                        if (!attribute.first.startsWith(relation.name)) {
                            this.add("FK$fk")
                            fk++
                        }
                    }.joinToString()
                }
            }
        }
    }
    spoiler({
        +"Функциональные зависимости"
    }, {
        hugeText(relation.functionals.set.map {
            "${it.from} -> ${it.to}" + if (it.cause != null) " (${it.cause})" else ""
        })
    })
    spoiler({
        +"Процесс получения ключей"
    }, {
        hugeText(keys.fullInfo.toString().split("\n").toList())
    })
    spoiler({
        +"Более красивая картинки - ERM"
    }, {
        img(
            src = "${relation.name}-erm.png",
            alt = "Тут должна была быть картинка ${relation.name} ERM, но по какой то причине она не прогрузилась"
        )
    })
    spoiler({
        +"Более красивая картинки - PDM"
    }, {
        img(
            src = "${relation.name}-pdm.png",
            alt = "Тут должна была быть картинка ${relation.name} PDM, но по какой то причине она не прогрузилась"
        )
    })
}