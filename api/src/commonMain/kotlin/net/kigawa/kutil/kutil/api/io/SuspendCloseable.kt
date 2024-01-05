package net.kigawa.mcsm.util.io

interface SuspendCloseable {
  suspend fun suspendClose()
}