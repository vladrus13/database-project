package ru.vladrus13.html.utils

import java.nio.file.Path

const val start = "/home/Vladislav.Kuznetsov/Vl/Projects/Git/database-project/site"

fun getStart(path: Path): String = if (start == "") "/$path" else Path.of(start).resolve(path).toString()
