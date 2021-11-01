package database.bean

class Result<T>(val preResult: PreResult, val result: T) {

    val shortInfo = preResult.shortInfo

    val info = preResult.info

    val fullInfo = preResult.fullInfo

    class PreResult {
        val shortInfo = StringBuilder()
        val info = StringBuilder()
        val fullInfo = StringBuilder()

        operator fun plusAssign(another: PreResult) {
            shortInfo.append(another.shortInfo)
            info.append(another.info)
            fullInfo.append(another.fullInfo)
        }
    }
}