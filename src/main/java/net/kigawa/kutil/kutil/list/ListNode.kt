package net.kigawa.kutil.kutil.list

import net.kigawa.kutil.kutil.concurrent.ConcurrentVar

class ListNode<T>(
  val list: KList<T>,
  front: ListNode<T>?,
  back: ListNode<T>?,
  val item: T,
) {
  val front = ConcurrentVar(front)
  val back = ConcurrentVar(back)
  val size: Int
    get() {
      return 1 + (back.get()?.size ?: 0)
    }
  
  fun getLast(): ListNode<T> {
    return back.get()?.getLast() ?: this
  }
  
  fun clone(list: KList<T>): ListNode<T> {
    return ListNode(list, front.get()?.cloneByBack(list, this), back.get()?.cloneByFront(list, this), item)
  }
  
  fun cloneByBack(list: KList<T>, back: ListNode<T>?): ListNode<T> {
    return ListNode(list, front.get()?.cloneByBack(list, this), back, item)
  }
  
  fun cloneByFront(list: KList<T>, front: ListNode<T>?): ListNode<T> {
    return ListNode(list, front, back.get()?.cloneByFront(list, this), item)
  }
}