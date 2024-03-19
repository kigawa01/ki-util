package net.kigawa.kutil.kutil.api.process

import net.kigawa.kutil.kutil.api.io.IoException
import net.kigawa.kutil.kutil.api.io.fs.KuDirectory

import java.io.IOException

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KuProcessBuilder actual constructor(vararg args: String) {
  private val processBuilder = ProcessBuilder(*args)

  actual fun start(): KuProcess {
    try {
      return JvmProcess(processBuilder.start())
    } catch (e: IOException) {
      throw IoException(e)
    }
  }

  actual fun workDir(directory: KuDirectory?): KuProcessBuilder {
    processBuilder.directory(directory?.nativeDirectory)
    return this
  }
}