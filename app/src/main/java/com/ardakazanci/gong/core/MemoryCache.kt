package com.ardakazanci.gong.core

object MemoryCache {
    private val map = HashMap<String, Any?>()

    fun put(key: String, value: Any?): MemoryCache {
        map[key] = value
        return this
    }

    fun get(key: String): Any? = map[key]

    fun have(key: String) = map.containsKey(key)

    fun contains(key: String) = have(key)

    fun clear() = map.clear()

    fun getAll() = map.toMap()

    fun getAllByType(clazz: Class<*>) = getAll().filter {
        val classValue = it.value
        classValue != null && classValue::class.java == clazz
    }
}

fun <T> getFromMemory(key: String): T? = MemoryCache.get(key) as? T

fun putInMemory(key: String, any: Any?) = MemoryCache.put(key, any)