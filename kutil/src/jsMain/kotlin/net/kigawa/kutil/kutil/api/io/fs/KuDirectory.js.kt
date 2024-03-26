package net.kigawa.kutil.kutil.api.io.fs

@Suppress( "unused")
actual class KuDirectory(
  private val path: KuPath,
) {
  actual fun toPath(): KuPath {
    return path
  }
}