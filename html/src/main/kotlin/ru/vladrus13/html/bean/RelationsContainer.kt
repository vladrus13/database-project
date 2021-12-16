package ru.vladrus13.html.bean

import ru.vladrus13.model.bean.Relations
import java.nio.file.Path

val pathToFiles: Path = Path.of("src").resolve("main").resolve("resources").resolve("input").resolve("tables")
val relationsContainer = Relations.read(pathToFiles)