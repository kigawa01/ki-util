package net.kigawa.kutil.kutil.api.io.fs

import net.kigawa.kutil.kutil.api.io.NotDirectoryException
import java.io.File

@Suppress("unused")
actual class KuDirectory(
  val nativeDirectory: File,
) {
  init {
    if (!nativeDirectory.isDirectory)
      throw NotDirectoryException("$nativeDirectory is not directory")
  }

  actual fun toPath(): KuPath {
    return KuPath(nativeDirectory.path)
  }
}