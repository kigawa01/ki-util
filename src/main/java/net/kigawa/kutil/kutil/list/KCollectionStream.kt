package net.kigawa.kutil.kutil.list

interface KCollectionStream<T>: Collection<T> {
  fun <R> modifyList(task: (MutableList<T>)->R): R
  fun toMutableList(): MutableList<T>
}