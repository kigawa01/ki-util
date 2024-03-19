package net.kigawa.kutil.kutil.api.io

import java.io.IOException

@Suppress("unused", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class IoException actual constructor(message: String?) : IOException(message) {
  constructor(exception: IOException) : this(exception.message)
}
