package net.kigawa.kutil.kutil.concurrent

class ConcurrentVar<T: Any?>(default: T): ConcurrentVal<T>(default) {
  @Synchronized
  fun set(value: T) {
    this.value = value
  }
}