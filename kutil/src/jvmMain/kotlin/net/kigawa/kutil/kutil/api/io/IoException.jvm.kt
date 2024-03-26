package net.kigawa.kutil.kutil.api.io

import java.io.IOException

@Suppress("unused")
actual open class IoException actual constructor(message: String?) : IOException(message) {
  constructor(exception: IOException) : this(exception.message)
}
