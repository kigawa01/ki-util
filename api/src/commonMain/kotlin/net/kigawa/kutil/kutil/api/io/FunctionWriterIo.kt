package net.kigawa.kutil.kutil.api.io

class FunctionWriterIo<T>(
  private val func: (T)->Unit
): WriterIo<T> {
  override suspend fun writeLine(value: T) {
    func(value)
  }
}