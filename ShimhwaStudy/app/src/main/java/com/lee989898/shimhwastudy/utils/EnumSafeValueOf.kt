package com.lee989898.shimhwastudy.utils

inline fun <reified T : Enum<T>> safeValueOf(type: String?): T? {
    if (type == null) return null
    return try {
        java.lang.Enum.valueOf(T::class.java, type)
    } catch (e: IllegalArgumentException) {
        null
    }
}