# Simple-Kache: An extremely light-weight cache framework for kotlin

A study case for learn how simple and lightweight cache strategies works.

# Implementations

The Kotlin implementations below uses the delegate pattern. You can check out the source code to understand how caching mechanics work and learn how to implement diverse strategies—such as LRU, FIFO, and memory-sensitive eviction—by yourself.

- [LRUKache](/src/main/kotlin/impl/LRUKache.kt): Discards the least recently accessed items when the cache limit is reached.
- [SoftKache](/src/main/kotlin/impl/SoftKache.kt): Uses [soft references](https://en.wikipedia.org/wiki/Soft_reference), retaining items until the system runs low on memory.
- [WeakKache](/src/main/kotlin/impl/WeakKache.kt): Uses [weak references](https://en.wikipedia.org/wiki/Weak_reference), allowing garbage collection when items are not held elsewhere.
- [FIFOKache](/src/main/kotlin/impl/FIFOKache.kt): Evicts the oldest inserted items first when the cache exceeds its capacity.
- [PerpetualKache](/src/main/kotlin/impl/PerpetualKache.kt): Persists items indefinitely, removing them only upon an explicit removal request.
- [ExpirableKache](/src/main/kotlin/impl/ExpirableKache.kt): Automatically clears the entire cache periodically based on a configured time interval.

