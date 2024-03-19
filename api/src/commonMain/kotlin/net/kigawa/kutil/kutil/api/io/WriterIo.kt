package net.kigawa.kutil.kutil.api.io

interface WriterIo<T> : Io {
  suspend fun writeLine(value: T)
}