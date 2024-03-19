package net.kigawa.kutil.kutil.api.io.fs

import java.io.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KuFile(
  val nativeFile: File,
) {
  fun remove() {
    nativeFile.delete()
  }
}