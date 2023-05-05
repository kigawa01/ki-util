package net.kigawa.kutil.kutil.concurrent

import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

interface ResultTask<T>: Task, Future<T> {
  fun get(timeoutMilli: Long): T
  override fun get(): T
  override fun get(timeout: Long, unit: TimeUnit): T
  
}