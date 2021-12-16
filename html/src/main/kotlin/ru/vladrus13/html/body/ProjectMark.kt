package ru.vladrus13.html.body

import kotlinx.html.BODY
import kotlinx.html.h1
import ru.vladrus13.html.utils.HTMLTree

open class ProjectMark(
    name: String,
    title: String,
    body: BODY.() -> Unit,
    children: Map<String, HTMLTree.Node> = hashMapOf()
) : HTMLTree.Node(name, title, {
    h1(classes = "main-title") {
        +"Vladislav Kuznetsov project: RPG"
    }
    body()
}, children)