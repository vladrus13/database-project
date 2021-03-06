package ru.vladrus13.model.utils

import ru.vladrus13.model.bean.Attributes
import ru.vladrus13.model.bean.Functional
import ru.vladrus13.model.bean.Functionals

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

        fun Functional.getString(): String {
            return this.from.getStringOfCollection(prefix = "[", postfix = "]") +
                    " -> " +
                    this.to.getStringOfCollection(prefix = "[", postfix = "]")
        }

        fun Functionals.getString(): String {
            return this.set.getStringOfCollection(
                separator = ",\n",
                prefix = "",
                postfix = "",
                transform = { it.getString() })
        }
    }
}