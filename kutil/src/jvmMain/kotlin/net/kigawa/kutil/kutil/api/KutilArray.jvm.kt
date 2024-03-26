package net.kigawa.kutil.kutil.api

@Suppress("unused")
actual object KutilArray {
  /**
   * test obj contain in array
   *
   * @param array array to test
   * @param obj   obj to test
   * @param <S>   source class type
   * @param <T>   test class type
   * @return return true when contain
  </T></S> */
  fun <T> contain(array: Array<out T>, obj: T): Boolean {
    return array.contains(obj)
  }

  /**
   * creat set from array
   *
   * @param ts  base array
   * @param <T> class type
   * @return created set
  </T> */
  fun <T> toSet(ts: Array<T>): Set<T> {
    return ts.toSet()
  }
}