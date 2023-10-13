@file:Suppress("unused")

package net.kigawa.kutil.kutil.api.manager

import net.kigawa.kutil.kutil.api.concurrent.Var
import net.kigawa.kutil.kutil.api.list.KList

@Suppress("MemberVisibilityCanBePrivate")
abstract class RemoveAble(
  parent: RemoveAble?,
) {
  private val removeTask = KList.create<Runnable>()
  open val parentField = parent
  private val registeredTask = parent?.registerRemoveTask(::remove)
  protected val removed = Var(false)
  fun isRemoved(): Boolean {
    return removed.get()
  }
  
  fun registerRemoveTask(runnable: Runnable): Runnable {
    removed.useValue {
      if (it) throw RemovedObjectException("this object is already removed")
      removeTask.add(runnable)
    }
    return runnable
  }
  
  fun unregisterRemoveTask(runnable: Runnable) {
    removed.useValue {
      if (it) throw RemovedObjectException("this object is already removed")
      removeTask.remove(runnable)
    }
  }
  
  open fun remove() {
    if (removed.useSelf {
        if (it) return@useSelf true
        set(true)
        false
      }) return
    
    registeredTask?.let {parentField?.unregisterRemoveTask(it)}
    removeTask.forEach {
      it.run()
    }
  }
}