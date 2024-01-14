package net.kigawa.mcsm.util.io

import net.kigawa.kutil.kutil.api.io.IoException
import java.io.IOException

class JvmIoException(message: String?) : IoException(message) {
  constructor(exception: IOException) : this(exception.message)
}

actual fun ioException(message: String?): IoException {
  return JvmIoException(message)
}