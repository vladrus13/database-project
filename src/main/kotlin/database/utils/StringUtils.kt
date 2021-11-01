package database.utils

import database.bean.Attributes

class StringUtils {
    companion object {
        fun <T> Collection<T>.getStringOfCollection(
            separator: String = ", ",
            prefix: String = "{",
            postfix: String = "}",
            transform: (T) -> String
        ): String {
            return this.joinToString(separator = separator, prefix = prefix, postfix = postfix, transform = transform)
        }

        fun Collection<String>.getStringOfCollection(
            separator: String = ", ",
            prefix: String = "{",
            postfix: String = "}"
        ): String {
            return this.joinToString(separator = separator, prefix = prefix, postfix = postfix)
        }

        fun Attributes.getStringOfAttributes(
            separator: String = ", ",
            prefix: String = "{",
            postfix: String = "}"
        ): String {
            return this.attributes.getStringOfCollection(separator, prefix, postfix)
        }
    }
}