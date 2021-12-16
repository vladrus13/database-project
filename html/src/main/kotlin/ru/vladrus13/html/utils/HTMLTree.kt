package ru.vladrus13.html.utils

import kotlinx.html.BODY
import ru.vladrus13.html.body.CSS
import ru.vladrus13.html.writeStandardHead
import java.nio.file.Files
import java.nio.file.Path

open class HTMLTree(private val start: Node) {

    open class Node(
        private val name: String,
        private val title: String,
        private val body: BODY.() -> Unit,
        private val children: Map<String, Node> = hashMapOf()
    ) {

        fun write(path: Path) {
            body.writeStandardHead(title, path.resolve("$name.html"))
            for (child in children) {
                val childPath = path.resolve(child.key)
                Files.createDirectories(childPath)
                child.value.write(childPath)
            }
        }
    }

    fun write(path: Path) {
        start.write(path)
        CSS(path.resolve("styles.css"))
    }
}