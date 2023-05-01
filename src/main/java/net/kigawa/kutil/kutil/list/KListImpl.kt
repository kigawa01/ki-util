@file:Suppress("unused")

package net.kigawa.kutil.kutil.list

import net.kigawa.kmccore.concurrent.ConcurrentBox

class KListImpl<T>(list: MutableList<T>): KList<T> {
  constructor(): this(mutableListOf<T>())
  constructor(initialCapacity: Int): this(ArrayList(initialCapacity))
  constructor(iterable: Iterable<T>): this(iterable.toMutableList())
  
  private val listBox = ConcurrentBox(list) {ArrayList(it)}
  
  @Synchronized
  override fun <R> modifyList(task: (MutableList<T>)->R): R {
    return listBox.modify(task)
  }
  
  override fun toMutableList(): MutableList<T> {
    return listBox.get()
  }
  
  override fun add(element: T): Boolean {
    return modifyList {
      it.add(element)
    }
  }
  
  override fun remove(element: T): Boolean {
    return modifyList {
      it.remove(element)
    }
  }
  
  override fun clone(): KList<T> {
    return KList.create(this)
  }
  
  override fun clear() {
    return modifyList {
      it.clear()
    }
  }
  
  override fun addAll(elements: Collection<T>): Boolean {
    return modifyList {
      it.addAll(elements)
    }
  }
  
  override fun addAll(index: Int, elements: Collection<T>): Boolean {
    return modifyList {
      it.addAll(index, elements)
    }
  }
  
  override fun add(index: Int, element: T) {
    return modifyList {
      it.add(index, element)
    }
  }
  
  override fun set(index: Int, element: T): T {
    return modifyList {
      it.set(index, element)
    }
  }
  
  override fun removeAll(elements: Collection<T>): Boolean {
    return modifyList {
      it.removeAll(elements)
    }
  }
  
  override fun toString(): String {
    return "ConcurrentList(${toMutableList()})"
  }
  
  override fun <R> map(transform: (T)->R): KList<R> {
    return mapTo(KListImpl<R>(this.size), transform)
  }
  
  override fun <R, C: MutableCollection<in R>> mapTo(destination: C, transform: (T)->R): C {
    for (item in this)
      destination.add(transform(item))
    return destination
  }
}