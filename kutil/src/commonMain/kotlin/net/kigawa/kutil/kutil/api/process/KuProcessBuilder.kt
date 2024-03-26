package net.kigawa.kutil.kutil.api.process

import net.kigawa.kutil.kutil.api.io.fs.KuDirectory

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class KuProcessBuilder(vararg args: String) {
  fun start(): KuProcess
  fun workDir(directory: KuDirectory?): KuProcessBuilder
}