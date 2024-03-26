package net.kigawa.kutil.kutil.api.process

import net.kigawa.kutil.kutil.api.io.fs.KuDirectory

actual class KuProcessBuilder actual constructor(vararg args: String) {
  actual fun start(): KuProcess {
    TODO("Not yet implemented")
  }

  @Suppress("unused")
  actual fun workDir(
    directory: KuDirectory?,
  ): KuProcessBuilder {
    TODO("Not yet implemented")
  }
}