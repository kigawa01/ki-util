package net.kigawa.kutil.kutil.api.io

import java.io.IOException

@Suppress("unused")
class IoException(message: String?) : IOException(message) {
  constructor(exception: IOException) : this(exception.message)
}
