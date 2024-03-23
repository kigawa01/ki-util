package net.kigawa.kutil.kutil.api.io.fs

import java.io.File

@Suppress("unused")
actual class KuFile(
  val nativeFile: File,
) {
  fun remove() {
    nativeFile.delete()
  }
}