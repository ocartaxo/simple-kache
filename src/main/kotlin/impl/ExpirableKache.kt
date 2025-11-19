package br.com.ocartaxo.impl

import br.com.ocartaxo.GenericKache
import java.util.concurrent.TimeUnit

/**
 * [ExpirableKache] flushes the items whose lifetime is longer than [flushInterval].
 */
class ExpirableKache<K, V>(
    private val delegate: GenericKache<K, V>,
    private val flushInterval: Long = TimeUnit.MINUTES.toMillis(1)
) : GenericKache<K, V> by delegate {
    private var lastFlushTime = System.nanoTime()

    override val size: Int
        get() {
            recycle()
            return delegate.size
        }

    override fun remove(key: K): V? {
        recycle()
        return delegate.remove(key)
    }

    override fun get(key: K): V? {
        recycle()
        return delegate[key]
    }

    private fun recycle() {
        val shouldRecycle = System.nanoTime() - lastFlushTime >= TimeUnit.MILLISECONDS.toNanos(flushInterval)
        if (shouldRecycle) {
            delegate.clear()
            lastFlushTime = System.nanoTime()
        }
    }
}