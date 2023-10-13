@file:Suppress("unused")

package net.kigawa.kutil.kutil.api.concurrent

open class Var<T: Any?>(
  protected var value: T,
) {
  @Synchronized
  fun set(value: T) {
    this.value = value
  }
  
  @Synchronized
  fun <R> useSelf(task: Var<T>. (T)->R): R {
    return task(value)
  }
  
  @Synchronized
  fun <R> useValue(task: (T)->R): R {
    return task(value)
  }
  
  @Synchronized
  fun modify(task: (T)->T) {
    value = task(value)
  }
  
  @Synchronized
  open fun get(): T {
    return value
  }
}