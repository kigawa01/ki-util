package net.kigawa.kutil.kutil.manager

import net.kigawa.kutil.kutil.list.KList

@Suppress("MemberVisibilityCanBePrivate")
abstract class RemoveAble(
  parent: RemoveAble?,
) {
  private val removeTask = KList.create<Runnable>()
  open val parentField = parent
  private val registeredTask = parent?.addRemoveTask(::remove)
  fun addRemoveTask(runnable: Runnable): Runnable {
    removeTask.add(runnable)
    return runnable
  }
  
  fun removeRemoveTask(runnable: Runnable) {
    removeTask.remove(runnable)
  }
  
  open fun remove() {
    registeredTask?.let {parentField?.removeRemoveTask(it)}
    removeTask.forEach {
      it.run()
    }
  }
}