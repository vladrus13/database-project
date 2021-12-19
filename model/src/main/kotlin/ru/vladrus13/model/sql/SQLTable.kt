package ru.vladrus13.model.sql

import ru.vladrus13.model.bean.Relation
import ru.vladrus13.model.bean.TypedAttributes
import ru.vladrus13.model.key.Keys.Companion.getKeys
import java.util.*

class SQLTable(
    private val name: String,
    private val rows: MutableList<SQLRow> = mutableListOf(),
    private val constraints: MutableList<Constraint> = mutableListOf()
) {

    sealed class Constraint(val table: SQLTable) {

        abstract fun getConstraint(): String
        override fun toString(): String {
            return "constraints " + getConstraint()
        }

        companion object {
            fun toConstraint(table: SQLTable, input: String): Constraint {
                val k = input.split(" ")
                val list = k.drop(1)
                return when (k[0]) {
                    "PRIMARY_KEY" -> {
                        PrimaryKey(list.map {
                            table.findByName(it) ?: throw IllegalStateException()
                        }, table)
                    }
                    "FOREIGN_KEY" -> {
                        ForeignKey(table.findByName(k[0])!!, k[1], k[2], table)
                    }
                    "KEY" -> {
                        Key(list.map {
                            table.findByName(it) ?: throw IllegalStateException()
                        }, table)
                    }
                    else -> {
                        throw IllegalStateException()
                    }
                }
            }
        }

        class ForeignKey(val from: SQLRow, private val toTable: String, private val column: String, table: SQLTable) :
            Constraint(table) {
            override fun getConstraint(): String {
                return table.name.lowercase() + "_" + from.name + "_fk foreign key (" + from.name + ") references " + toTable + "(" + column + ")"
            }

        }

        class PrimaryKey(val keys: List<SQLRow>, table: SQLTable) : Constraint(table) {
            override fun getConstraint(): String {
                return table.name.lowercase() + "_" + keys.joinToString(separator = "_") { it.name.lowercase() } + "_pk foreign key (" +
                        keys.joinToString { it.name } + ")"

            }

        }

        class Key(val keys: List<SQLRow>, table: SQLTable) : Constraint(table) {
            override fun getConstraint(): String {
                return table.name.lowercase() + "_" + keys.joinToString(separator = "_") { it.name.lowercase() } + "_k key (" +
                        keys.joinToString { it.name } + ")"
            }
        }
    }

    class SQLRow(val name: String, val type: String, val table: SQLTable) {
        override fun toString(): String {
            return "$name $type"
        }
    }

    companion object {
        fun toSQL(relation: Relation): SQLTable {
            val typed = TypedAttributes.toTyped(relation)
            val sqlTable =
                SQLTable(relation.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
            for (attribute in typed) {
                sqlTable.rows.add(SQLRow(attribute.first, attribute.second, sqlTable))
            }
            val keys = relation.getKeys()
            val realColumns = keys.result.map { key ->
                key.map {
                    sqlTable.findByName(it)!!
                }
            }
            sqlTable.constraints.add(Constraint.PrimaryKey(realColumns[0], sqlTable))
            realColumns.drop(1).forEach {
                sqlTable.constraints.add(Constraint.Key(it, sqlTable))
            }
            for (column in sqlTable.rows) {
                if (!column.name.startsWith(relation.name)) {
                    val tableName = column.name.split("_")[0]
                    val anotherColumn = column.name.split("-")[0]
                    sqlTable.constraints.add(Constraint.ForeignKey(column, tableName, anotherColumn, sqlTable))
                }
            }
            return sqlTable
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.appendLine("create table $name")
        sb.appendLine("(")
        sb.appendLine((rows.map {
            it.toString()
        } + constraints.map {
            it.toString()
        }).joinToString(separator = ",\n"))
        sb.appendLine(");")
        return sb.toString()
    }

    fun findByName(name: String): SQLRow? {
        return rows.filter { it.name == name }.let {
            if (it.isNotEmpty()) it[0]
            else null
        }
    }
}