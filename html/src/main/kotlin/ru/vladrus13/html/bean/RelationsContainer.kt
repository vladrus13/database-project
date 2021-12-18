package ru.vladrus13.html.bean

import ru.vladrus13.model.bean.Relations
import ru.vladrus13.model.utils.pathToResources
import java.nio.file.Path

val pathToFiles: Path = pathToResources.resolve("input").resolve("tables")
val relationsContainer = Relations.read(pathToFiles)