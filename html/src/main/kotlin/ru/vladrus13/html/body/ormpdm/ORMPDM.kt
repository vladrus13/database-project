package ru.vladrus13.html.body.ormpdm

import kotlinx.html.br
import kotlinx.html.h2
import kotlinx.html.h4
import kotlinx.html.img
import ru.vladrus13.html.bean.spoiler
import ru.vladrus13.html.utils.HTMLProjectFile
import ru.vladrus13.html.utils.NestedHTMLFile
import ru.vladrus13.model.utils.pathToResources
import java.nio.file.Path
import javax.imageio.ImageIO
import kotlin.io.path.readText

object ORMPDM : NestedHTMLFile(
    HTMLProjectFile(
        name = "ormpdm",
        htmlTitle = "ORM-PDM",
        body = {
            h2 {
                +"ERM"
            }
            br()
            img(src = "erm.png", alt = "Здесь должна быть картинка ERM")
            br()
            spoiler({
                +"Обьяснения"
            }, {
                val proofs = pathToResources.resolve("input").resolve("proofs").resolve("pic.proof").readText()
                proofs.split("\n").forEach { proof ->
                    val splitted = proof.split("->->").map { it.trim() }.filter { it.isNotBlank() }
                    if (splitted.size != 4) throw IllegalStateException()
                    val from = splitted[0]
                    val to = splitted[1]
                    val fromTo = splitted[2]
                    val toFrom = splitted[3]
                    h4 {
                        +"Отношение $from-$to"
                    }
                    +fromTo
                    br()
                    h4 {
                        +"Отношение $to-$from"
                    }
                    +toFrom

                }
                br()
            })

            br()
            h2 {
                +"PDM"
            }
            br()
            img(src = "pdm.png", alt = "Здесь должна быть картинка PDM")
        }
    )
) {
    override fun beforeSave(path: Path) {
        val pathToPictures: Path = pathToResources.resolve("input").resolve("pic")
        val erm = ImageIO.read(pathToPictures.resolve("ERM.png").toFile())
        val pdm = ImageIO.read(pathToPictures.resolve("PDM.png").toFile())
        ImageIO.write(erm, "png", path.resolve("erm.png").toFile())
        ImageIO.write(pdm, "png", path.resolve("pdm.png").toFile())
    }
}