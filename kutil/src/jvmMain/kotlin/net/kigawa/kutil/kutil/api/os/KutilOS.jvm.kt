package net.kigawa.kutil.kutil.api.os

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.kigawa.kutil.kutil.api.KutilPlatform
import net.kigawa.kutil.kutil.api.io.DefaultIo
import net.kigawa.kutil.kutil.api.Version
import net.kigawa.kutil.kutil.api.process.KuProcessBuilder

@Suppress("MemberVisibilityCanBePrivate", "unused")
actual object KutilOS {
  private val OS_NAME = KutilPlatform.getSystemProperty("os.name")
  val OS_TYPE = getOsType()
  val OS_VERSION = getVersion()

  private fun getOsType(): OSType {
    if (OS_NAME.lowercase().startsWith("win")) return OSType.WINDOWS
    if (OS_NAME.lowercase().startsWith("mac")) return OSType.MAC
    if (OS_NAME.lowercase().startsWith("linux")) {
      return getLinuxType()
    }
    return OSType.OTHER
  }

  private fun getVersion(): Version? {
    return when (OS_TYPE) {
      OSType.LINUX -> {
        val process = KuProcessBuilder("lsb_release", "-i").start()
        CoroutineScope(Dispatchers.IO).launch {
          process.errReader().forEach {
            DefaultIo.error.writeLine(it)
          }
        }

        val strVersion = runBlocking {
          process
            .reader()
            .read()
        }.split(":")
          .getOrNull(1)
          ?.trim()
          ?: return null

        return Version(strVersion)
      }

      else -> null
    }
  }

  private fun getLinuxType(): OSType {
    val process = KuProcessBuilder("lsb_release", "-i").start()
    CoroutineScope(Dispatchers.IO).launch {
      process.errReader().forEach {
        DefaultIo.error.writeLine(it)
      }
    }
    val id = runBlocking {
      process
        .reader()
        .read()
    }.split(":")
      .getOrNull(1)
      ?.trim()
      ?: return OSType.LINUX

    if (id.lowercase() == "ubuntu") return OSType.UBUNTU
    return OSType.LINUX
  }
}