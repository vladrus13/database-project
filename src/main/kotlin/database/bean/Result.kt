package database.bean

class Result<T>(private val preResult : PreResult, val result : T) {

    val shortInfo
        get() = preResult.shortInfo

    val info
        get() = preResult.info

    val fullInfo
        get() = preResult.fullInfo

    class PreResult {
        val shortInfo = StringBuilder()
        val info = StringBuilder()
        val fullInfo = StringBuilder()

        fun shortInfo(s : String) {
            shortInfo.appendLine(s)
        }

        fun info(s : String) {
            info.appendLine(s)
        }

        fun fullInfo(s : String) {
            fullInfo.appendLine(s)
        }

        operator fun plusAssign(another : PreResult) {
            shortInfo.appendLine(another.shortInfo)
            info.appendLine(another.info)
            fullInfo.appendLine(another.fullInfo)
        }
    }
}