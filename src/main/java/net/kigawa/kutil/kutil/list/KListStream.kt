package net.kigawa.kutil.kutil.list

interface KListStream<T>: List<T>, KCollectionStream<T> {
  
  /**
   * Returns 1st *element* from the list.
   *
   * Throws an [IndexOutOfBoundsException] if the size of this list is less than 1.
   */
  fun component1(): T {
    return get(0)
  }
  
  /**
   * Returns 2nd *element* from the list.
   *
   * Throws an [IndexOutOfBoundsException] if the size of this list is less than 2.
   */
  fun component2(): T {
    return get(1)
  }
  
  /**
   * Returns 3rd *element* from the list.
   *
   * Throws an [IndexOutOfBoundsException] if the size of this list is less than 3.
   */
  fun component3(): T {
    return get(2)
  }
  
  /**
   * Returns 4th *element* from the list.
   *
   * Throws an [IndexOutOfBoundsException] if the size of this list is less than 4.
   */
  fun component4(): T {
    return get(3)
  }
  
  /**
   * Returns 5th *element* from the list.
   *
   * Throws an [IndexOutOfBoundsException] if the size of this list is less than 5.
   */
  fun component5(): T {
    return get(4)
  }
  
  fun <R> map(transform: (T)->R): KList<R>
  fun <R, C: MutableCollection<in R>> mapTo(destination: C, transform: (T)->R): C
}