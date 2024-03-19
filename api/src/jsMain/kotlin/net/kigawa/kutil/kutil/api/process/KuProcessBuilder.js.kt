package net.kigawa.kutil.kutil.api.process

import net.kigawa.kutil.kutil.api.io.fs.KuDirectory

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KuProcessBuilder actual constructor(vararg args: String) {
  actual fun start(): KuProcess {
    TODO("Not yet implemented")
  }

  actual fun workDir(
    directory: KuDirectory?,
  ): KuProcessBuilder {
    TODO("Not yet implemented")
  }
}