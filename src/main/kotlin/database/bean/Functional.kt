package database.bean

class Functional(val from: Set<String>, val to: Set<String>) {
    companion object {
        fun read(functional: String): Functional {
            val splitted = functional.split("->").map { it.trim() }
            check(splitted.size == 2) { "Can't read from functional! Wrong count of -\">\"" }
            val from = splitted[0].split(",").map { it.trim() }
            val to = splitted[1].split(",").map { it.trim() }
            return Functional(from.toSet(), to.toSet())
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Functional
        if (from != other.from) return false
        if (to != other.to) return false

        return true
    }

    override fun hashCode(): Int {
        var result = from.hashCode()
        result = 31 * result + to.hashCode()
        return result
    }

    fun asString(separator: String = " -> ", prefix: String = "", postfix: String = ""): String {
        return prefix + from.joinToString() + separator + to.joinToString() + postfix
    }

    fun checkContains(attributes: Attributes) {
        check(attributes.attributes.containsAll(from)) { "Attributes doesn't contain all from functional" }
        check(attributes.attributes.containsAll(to)) { "Attributes doesn't contain all to functional" }
    }
}