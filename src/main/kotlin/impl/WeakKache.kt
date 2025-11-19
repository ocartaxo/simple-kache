package br.com.ocartaxo.impl

import br.com.ocartaxo.Kache
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference

/**
 * [WeakKache] caches items with a [WeakReference] wrapper.
 */
class WeakKache(
    private val delegate: Kache
) : Kache by delegate {
    private val referenceQueue = ReferenceQueue<Any>()

    private class WeakEntry(
        val key: Any,
        value: Any,
        referenceQueue: ReferenceQueue<Any>
    ) : WeakReference<Any>(value, referenceQueue)

    override fun set(key: Any, value: Any) {
        removeUnreachableItems()
        val weakEntry = WeakEntry(key, value, referenceQueue)
        delegate[key] = weakEntry
    }

    override fun remove(key: Any) {
        delegate.remove(key)
        removeUnreachableItems()
    }

    override fun get(key: Any): Any? {
        val weakEntry = delegate[key] as WeakEntry?
        weakEntry?.get()?.let { return it }
        delegate.remove(key)
        return null
    }

    private fun removeUnreachableItems() {
        var weakEntry = referenceQueue.poll() as WeakEntry?
        while (weakEntry != null) {
            val key = weakEntry.key
            delegate.remove(key)
            weakEntry = referenceQueue.poll() as WeakEntry?
        }
    }
}