package net.kigawa.mcsm.util.io

interface WriterIo<T> : Io {
  suspend fun writeLine(value: T)
}