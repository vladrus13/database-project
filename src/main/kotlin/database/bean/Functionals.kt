package database.bean

data class Functionals(val set: Set<Functional>) {
    companion object {
        fun read(s : String) : Functionals {
            return Functionals(s.split("\\s+".toRegex()).map {
                Functional.read(it)
            }.toSet())
        }
    }
}