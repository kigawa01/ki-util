package net.kigawa.kutil.kutil.api.io

interface SuspendCloseable {
  suspend fun suspendClose()
}