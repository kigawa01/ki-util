package net.kigawa.kutil.kutil.list

import java.util.*

interface KList<T>: MutableList<T>, KListStream<T> {
  companion object {
    @JvmStatic
    fun <T> create() = KListImpl<T>(LinkedList())
    
    @JvmStatic
    fun <T> create(iterable: Iterable<T>) = KListImpl(LinkedList(iterable.toList()))
  }
  
  override val size: Int
    get() = toMutableList().size
  
  override fun lastIndexOf(element: T): Int {
    return toMutableList().lastIndexOf(element)
  }
  
  override fun indexOf(element: T): Int {
    return toMutableList().indexOf(element)
  }
  
  override fun containsAll(elements: Collection<T>): Boolean {
    return toMutableList().containsAll(elements)
  }
  
  override fun contains(element: T): Boolean {
    return toMutableList().contains(element)
  }
  
  override fun get(index: Int): T {
    return toMutableList()[index]
  }
  
  override fun isEmpty(): Boolean {
    return toMutableList().isEmpty()
  }
  
  override fun iterator(): MutableIterator<T> {
    return toMutableList().iterator()
  }
  
  override fun listIterator(): MutableListIterator<T> {
    return toMutableList().listIterator()
  }
  
  override fun listIterator(index: Int): MutableListIterator<T> {
    return toMutableList().listIterator(index)
  }
  
  override fun removeAt(index: Int): T {
    return toMutableList().removeAt(index)
  }
  
  override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
    return toMutableList().subList(fromIndex, toIndex)
  }
  
  override fun retainAll(elements: Collection<T>): Boolean {
    return toMutableList().retainAll(elements)
  }
  
  fun clone(): KList<T>
}