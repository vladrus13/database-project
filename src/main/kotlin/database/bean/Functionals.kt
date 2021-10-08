package database.bean

data class Functionals(val set: Set<Functional>) {
    companion object {
        fun read(s : String) : Functionals {
            return Functionals(s.split("\n").map {
                Functional.read(it)
            }.toSet())
        }
    }

    operator fun iterator() : Iterator<Functional> {
        return set.iterator()
    }
}