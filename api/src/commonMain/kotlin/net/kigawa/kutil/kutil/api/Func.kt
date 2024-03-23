package net.kigawa.kutil.kutil.api

import kotlinx.coroutines.channels.Channel


suspend inline fun <T> Channel<T>.forEach(func: (T) -> Unit) {
  for (item in this) {
    func(item)
  }
}
