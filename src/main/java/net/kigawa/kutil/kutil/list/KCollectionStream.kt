package net.kigawa.kutil.kutil.list

interface KCollectionStream<T>: Collection<T> {
  fun toMutableList(): MutableList<T>
}