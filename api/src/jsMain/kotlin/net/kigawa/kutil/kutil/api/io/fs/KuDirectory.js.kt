package net.kigawa.kutil.kutil.api.io.fs

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KuDirectory(
  private val path: KuPath,
) {
  actual fun toPath(): KuPath {
    return path
  }
}