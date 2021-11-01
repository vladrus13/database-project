package database

import database.step.Steps
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.outputStream

fun main() {
    val path = Path.of("src").resolve("main").resolve("resources").resolve("output")
    Files.createDirectories(path)
    Steps.runSteps(
        path.resolve("shortInfo.txt").outputStream().bufferedWriter(),
        path.resolve("info.txt").outputStream().bufferedWriter(),
        path.resolve("fullInfo.txt").outputStream().bufferedWriter()
    )
}