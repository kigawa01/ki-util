@file:Suppress("unused")

package net.kigawa.kutil.kutil.api.diff

object KutilDiff {
  
  @JvmStatic
  fun <T> getDiff(oldIterable: Iterable<T>, newIterable: Iterable<T>): Diff<List<T>> {
    return Diff(
      newIterable.filter {
        !oldIterable.contains(it)
      },
      oldIterable.filter {
        !newIterable.contains(it)
      }
    )
  }
}