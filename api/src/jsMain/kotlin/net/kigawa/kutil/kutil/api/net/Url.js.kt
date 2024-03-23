package net.kigawa.kutil.kutil.api.net

import net.kigawa.kutil.kutil.api.io.fs.KuPath

@Suppress("unused")
actual class Url actual constructor(strUrl: String) {
  actual suspend fun download(to: KuPath) {
  }
}