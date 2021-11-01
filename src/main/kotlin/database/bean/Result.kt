package database.bean

class Result<T>(preResult: PreResult, val result: T) {

    val shortInfo = preResult.shortInfo

    val info = preResult.info

    val fullInfo = preResult.fullInfo

    class PreResult {
        val shortInfo = StringBuilder()
        val info = StringBuilder()
        val fullInfo = StringBuilder()

        fun shortInfo(s: String) = shortInfo.appendLine(s)

        fun info(s: String) = info.appendLine(s)

        fun fullInfo(s: String) = fullInfo.appendLine(s)

        operator fun plusAssign(another: PreResult) {
            shortInfo.appendLine(another.shortInfo)
            info.appendLine(another.info)
            fullInfo.appendLine(another.fullInfo)
        }
    }
}