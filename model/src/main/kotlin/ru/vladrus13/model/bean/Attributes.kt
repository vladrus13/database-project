package ru.vladrus13.model.bean

import com.google.gson.annotations.SerializedName

data class Attributes(@SerializedName("attributes") val attributes: Set<String>) {

    companion object {
        fun read(attributes: String, separator: String = "\n"): Attributes {
            return Attributes(attributes.split(separator).map { it.trim() }.toSet())
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Attributes
        if (attributes != other.attributes) return false
        return true
    }

    override fun hashCode(): Int {
        return attributes.hashCode()
    }

    fun asString(separator: String = ", ", prefix: String = "(", postfix: String = ")"): String {
        return attributes.joinToString(separator = separator, prefix = prefix, postfix = postfix)
    }

    operator fun iterator(): Iterator<String> {
        return attributes.iterator()
    }
}