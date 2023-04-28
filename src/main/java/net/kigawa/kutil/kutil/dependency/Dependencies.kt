package net.kigawa.kutil.kutil.dependency

import java.util.*

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