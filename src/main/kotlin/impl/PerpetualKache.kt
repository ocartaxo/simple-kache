package br.com.ocartaxo.impl

import br.com.ocartaxo.GenericKache


/**
 * [PerpetualKache] caches the items perpetually unless they're manually [remove]ed.
 */
class PerpetualKache<K, V> : GenericKache<K, V> {
    private val cache = hashMapOf<K, V>()

    override val size: Int
        get() = cache.size

    override fun set(key: K, value: V) {
        cache[key] = value
    }

    override fun get(key: K): V? = cache[key]

    override fun remove(key: K): V? = cache.remove(key)

    override fun clear() = cache.clear()
}




