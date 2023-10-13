package net.kigawa.kutil.kutil.api.err

interface ErrorHandler<T: Throwable> {
  fun <R> tryCatch(eClass: Class<out T>, task: ()->R) {
    try {
      task()
    } catch (e: Throwable) {
      @Suppress("UNCHECKED_CAST")
      if (eClass.isInstance(e)) catch(e as T)
      else throw e
    }
  }
  
  fun <R> tryCatch(task: ()->R)
  fun catch(e: T)
}