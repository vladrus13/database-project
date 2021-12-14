package ru.vladrus13.model.bean

class Result<T>(val preResult: PreResult, val result: T) {

    val shortInfo = preResult.shortInfo

    val info = preResult.info

    val fullInfo = preResult.fullInfo

    open class PreResult {
        internal val shortInfo = StringBuilder()
        internal val info = StringBuilder()
        internal val fullInfo = StringBuilder()

        fun fullInfoAppend(s: String): StringBuilder = fullInfo.append(s)

        fun infoAppend(s: String): java.lang.StringBuilder? {
            fullInfo.append(s)
            return info.append(s)
        }

        fun shortInfoAppend(s: String): java.lang.StringBuilder? {
            fullInfo.append(s)
            info.append(s)
            return shortInfo.append(s)
        }

        fun fullInfoAppendLine(s: String) = fullInfo.appendLine(s)

        fun infoAppendLine(s: String): StringBuilder {
            fullInfo.appendLine(s)
            return info.appendLine(s)
        }

        fun shortInfoAppendLine(s: String): StringBuilder {
            fullInfo.appendLine(s)
            info.appendLine(s)
            return shortInfo.appendLine(s)
        }

        operator fun plusAssign(another: PreResult) {
            shortInfo.append(another.shortInfo)
            info.append(another.info)
            fullInfo.append(another.fullInfo)
        }
    }
}