package ru.vladrus13.html.utils

import kotlinx.html.BODY
import kotlinx.html.h1
import ru.vladrus13.html.writeStandardHead
import java.nio.file.Files
import java.nio.file.Path

abstract class FileSystem {
    abstract fun save(path: Path)
}

class Folder(private val name: String, private val fileSystem: List<FileSystem>) : FileSystem() {
    override fun save(path: Path) {
        val real = path.resolve(name)
        Files.createDirectories(real)
        for (file in fileSystem) {
            file.save(real)
        }
    }
}

open class Root(private val fileSystem: List<FileSystem>) : FileSystem() {
    override fun save(path: Path) {
        for (file in fileSystem) {
            file.save(path)
        }
    }

}

abstract class File(private val name: String, private val extension: String) : FileSystem() {

    abstract fun saveFile(path: Path)

    override fun save(path: Path) {
        saveFile(path.resolve("$name.$extension"))
    }
}

open class HTMLFile(name: String, private val htmlTitle: String, private val body: BODY.() -> Unit) :
    File(name, "html") {
    override fun saveFile(path: Path) {
        body.writeStandardHead(htmlTitle, path)
    }
}

class HTMLProjectFile(name: String, htmlTitle: String, body: BODY.() -> Unit) : HTMLFile(name, htmlTitle, {
    h1(classes = "main-title") {
        +"Vladislav Kuznetsov project: RPG"
    }
    body()
})

open class NestedHTMLFile(private val htmlFile: HTMLProjectFile, private val next: FileSystem? = null) : FileSystem() {

    open fun beforeSave(path: Path) {

    }

    override fun save(path: Path) {
        beforeSave(path)
        htmlFile.save(path)
        next?.save(path)
        afterSave(path)
    }

    open fun afterSave(path: Path) {

    }
}
