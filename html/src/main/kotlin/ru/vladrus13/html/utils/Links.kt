package ru.vladrus13.html.utils

import java.nio.file.Path

val start: Path = Path.of("/home/Vladislav.Kuznetsov/Vl/Projects/Git/database-project/site")

fun getStart(path: Path): Path = start.resolve(path)