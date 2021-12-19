package ru.vladrus13.html.body.ormpdm

import kotlinx.html.br
import kotlinx.html.img
import ru.vladrus13.html.utils.HTMLProjectFile
import ru.vladrus13.html.utils.NestedHTMLFile
import ru.vladrus13.model.utils.pathToResources
import java.nio.file.Path
import javax.imageio.ImageIO

object ORMPDM : NestedHTMLFile(
    HTMLProjectFile(
        name = "ormpdm",
        htmlTitle = "ORM-PDM",
        body = {
            +"ERM"
            br()
            img(src = "erm.png", alt = "Здесь должна быть картинка ERM")
            br()
            +"PDM"
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