package net.kigawa.kutil.kutil.manager

import net.kigawa.kmccore.concurrent.ConcurrentSet

abstract class Manager<T: ManagedEntry<T, *>> {
  @Suppress("MemberVisibilityCanBePrivate")
  protected val entries = ConcurrentSet<T>()
  open fun add(entry: T) {
    entries.add(entry)
  }
  
  open fun remove(entry: T) {
    entries.remove(entry)
  }
  
  fun getEntries(): MutableList<T> {
    return entries.toMutableList()
  }
}