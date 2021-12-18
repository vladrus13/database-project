package ru.vladrus13

import ru.vladrus13.tornado.ProjectApp
import tornadofx.launch

fun main() {
    launch<ProjectApp>()
    /*
    val path = pathToInput.resolve("output")
    Files.createDirectories(path)
    Steps.runSteps(
        path.resolve("shortInfo.txt").outputStream().bufferedWriter(),
        path.resolve("info.txt").outputStream().bufferedWriter(),
        path.resolve("fullInfo.txt").outputStream().bufferedWriter()
    )*/
}