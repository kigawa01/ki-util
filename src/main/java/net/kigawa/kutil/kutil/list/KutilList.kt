@file:Suppress("unused")

package net.kigawa.kutil.kutil.list

object KutilList

fun <T: Collection<E>, E> T.contains(filter: (E)->Boolean): Boolean {
  forEach {
    if (filter(it)) return true
  }
  return false
}
