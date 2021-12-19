package ru.vladrus13.html.body.createtable

import kotlinx.html.h3
import kotlinx.html.hr
import kotlinx.html.img
import ru.vladrus13.html.bean.hugeText
import ru.vladrus13.html.bean.relationTable
import ru.vladrus13.html.bean.relationsContainer
import ru.vladrus13.html.utils.HTMLProjectFile
import ru.vladrus13.html.utils.NestedHTMLFile
import ru.vladrus13.model.bean.SQLTypesAttributes
import ru.vladrus13.model.bean.TypedAttributes
import ru.vladrus13.model.key.Keys.Companion.getKeys
import ru.vladrus13.model.utils.pathToResources
import ru.vladrus13.pictures.imageTable
import ru.vladrus13.pictures.utils.Font.Companion.jetbrainsBold
import ru.vladrus13.pictures.utils.Font.Companion.jetbrainsBoldItalic
import ru.vladrus13.pictures.utils.Font.Companion.jetbrainsItalic
import ru.vladrus13.pictures.utils.Font.Companion.jetbrainsNormal
import java.nio.file.Path
import javax.imageio.ImageIO

object TablesCreate : NestedHTMLFile(
    HTMLProjectFile(
        name = "createtable",
        htmlTitle = "О Создании",
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
            for (relation in relationsContainer) {
                relationTable(relation)
                hr()
                hr()
            }
            h3 {
                +"Примерная картинка"
            }
            img(src = "preview.png", alt = "Здесь должна быть картинка")
        }
    )
) {

    override fun afterSave(path: Path) {
        super.afterSave(path)
        for (relation in relationsContainer) {
            val keys = relation.getKeys()
            val typed = TypedAttributes.toTyped(relation)
            val listERM = mutableListOf(listOf(Pair(relation.name, jetbrainsBold)))
            for (attribute in typed.attributes.filter { it.first.startsWith(relation.name) }) {
                val name = attribute.first
                val type = attribute.second
                listERM.add(listOf(Pair(name, jetbrainsNormal), Pair(type, jetbrainsNormal)))
            }
            val imageERM = imageTable(listERM)
            ImageIO.write(imageERM, "png", path.resolve("${relation.name}-erm.png").toFile())
            val sql = SQLTypesAttributes.toTyped(typed)
            val listPDM = mutableListOf(listOf(Pair(relation.name, jetbrainsBold)))
            var fk = 0
            for (attribute in sql) {
                val name = attribute.first
                val type = attribute.second
                var isFK = false
                var isPK = false
                val k = mutableListOf<String>().apply {
                    keys.result.forEachIndexed { index, key ->
                        if (key.contains(attribute.first)) {
                            this.add("PK$index")
                            isPK = true
                        }
                    }
                    if (!attribute.first.startsWith(relation.name)) {
                        this.add("FK$fk")
                        isFK = true
                        fk++
                    }
                }.joinToString()
                val font = if (isFK and isPK) {
                    jetbrainsBoldItalic
                } else {
                    if (isFK) {
                        jetbrainsItalic
                    } else {
                        if (isPK) {
                            jetbrainsBold
                        } else {
                            jetbrainsNormal
                        }
                    }
                }
                listPDM.add(listOf(Pair(name, font), Pair(type, font), Pair(k, font)))
            }
            val imagePDM = imageTable(listPDM)
            ImageIO.write(imagePDM, "png", path.resolve("${relation.name}-pdm.png").toFile())
        }
        val pathOfPreview = pathToResources.resolve("input").resolve("pic").resolve("preview.png")
        val image = ImageIO.read(pathOfPreview.toFile())
        ImageIO.write(image, "png", path.resolve("preview.png").toFile())
    }
}