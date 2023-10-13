@file:Suppress("unused")

package net.kigawa.kutil.kutil.api.concurrent

class Box<T>(item: T, private val clone: (T)->T): Var<T>(item) {
  
  override fun get(): T {
    return useValue(clone)
  }
  
  fun clone(): Box<T> {
    return Box(get(), clone)
  }
}