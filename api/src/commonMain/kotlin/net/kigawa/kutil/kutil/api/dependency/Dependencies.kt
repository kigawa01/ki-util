package net.kigawa.kutil.kutil.api.dependency

import java.util.*

@Suppress("unused")
class DependencyStack<T>(
  private val stack: List<T>,
) {
  constructor(): this(mutableListOf())
  
  fun add(dependency: T): DependencyStack<T> {
    if (stack.contains(dependency)) throw DependencyCirculationException("unit has bean circular reference", dependency)
    val list = LinkedList(stack)
    list.add(dependency)
    return DependencyStack(stack)
  }
}