package ru.vladrus13.html.body.createtable

import kotlinx.html.h3
import kotlinx.html.hr
import ru.vladrus13.html.bean.hugeText
import ru.vladrus13.html.bean.relationTable
import ru.vladrus13.html.body.ProjectMark
import ru.vladrus13.model.bean.Relations
import java.nio.file.Path

val pathToFiles: Path = Path.of("src").resolve("main").resolve("resources").resolve("input").resolve("tables")

object TablesCreate : ProjectMark(
    name = "createtable",
    title = "О Создании",
    body = {
        hugeText(
            listOf(
                "Давайте разберем что нам вообще нужно завести",
                "У нас есть пользователи, так что нам нужно будет завести табличку для этого. У пользователей должна быть своя табличка",
                "У нас есть предметы. Заведем для них отношение тоже",
                "Теперь есть сложность с абстрактными персонажами",
                "Конечно же, весьма затратно будет заводить для каждого персонажа отдельную стандартную статистику, " +
                        "поэтому должно быть реализовано наследование между абстрактным классом персонажей и врагами с НПС. " +
                        "Так же, не очень хорошо будет для каждого врага одного типа пользоваться новой копией данных",
                "Я не хочу делать широкую табличку, потому что не люблю null, а их будет много",
                "Я не хочу делать таблицу конкретных классов, потому что сложно будет управлять разными классами в глубоком наследовании",
                "Однако у нас есть безвыходность в плане юнитов, уже размешенных на карте. Будет сложно делать проверки на совпадение в координатах. Придется делать широкую табличку",
                "Наш выбор - таблицы классов"
            )
        )
        hr()
        h3 {
            +"Отношения"
        }
        hr()
        val relations = Relations.read(pathToFiles)
        for (relation in relations) {
            relationTable(relation)
        }
    })