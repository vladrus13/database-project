package ru.vladrus13.html

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import ru.vladrus13.html.utils.getStart
import java.nio.file.Path
import kotlin.io.path.bufferedWriter

fun (BODY.() -> Unit).writeStandardHead(title: String, path: Path) {
    val head: HEAD.() -> Unit = {
        link(
            rel = "stylesheet",
            href = getStart(Path.of("styles.css")).toString(),
            type = "text/css"
        )
        meta(charset = "utf-8")
        title(title)
    }
    write(head, this, path)
}

fun write(head: HEAD.() -> Unit, body: BODY.() -> Unit, path: Path) {
    val invoked: HTML.() -> Unit = {
        head {
            head(this)
        }
        body {
            body(this)
        }
    }
    invoked.write(path)
}

fun (HTML.() -> Unit).write(path: Path) = path.bufferedWriter().use {
    it.appendHTML().html {
        this@write()
    }
}