package ru.vladrus13.model.sql

import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.TypedAttributes
import ru.vladrus13.model.utils.pathToResources
import kotlin.io.path.readText

val constraints = pathToResources.resolve("input").resolve("sql").resolve("constraints.in")
    .readText().split("\n").map {

    }

class SQLTable(private val rows: List<SQLRow>, private val constraints: List<Constraint> = listOf()) {

    sealed class Constraint {

        companion object {
            fun toConstraint(table: SQLTable, input: String): Constraint {
                val k = input.split(" ")
                val list = k.drop(1).map {
                    table.findByName(it) ?: throw IllegalStateException()
                }
                return when (k[0]) {
                    "PRIMARY_KEY" -> {
                        PrimaryKey(list)
                    }
                    "FOREIGN_KEY" -> {
                        ForeignKey(list)
                    }
                    "KEY" -> {
                        Key(list)
                    }
                    else -> {
                        throw IllegalStateException()
                    }
                }
            }
        }

        class ForeignKey(val keys: List<SQLRow>) : Constraint() {

        }

        class PrimaryKey(val keys: List<SQLRow>) : Constraint() {

        }

        class Key(val keys: List<SQLRow>) : Constraint() {

        }
    }

    class SQLRow(val name: String, val type: String)

    companion object {
        fun toSQL(relation: Relation): SQLTable {
            val typed = TypedAttributes.toTyped(relation)
            TODO()
        }
    }

    fun findByName(name: String): SQLRow? {
        return rows.filter { it.name == name }.let {
            if (it.isNotEmpty()) it[0]
            else null
        }
    }
}