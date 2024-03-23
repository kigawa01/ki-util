package net.kigawa.kutil.kutil.api

import net.kigawa.kutil.kutil.api.io.fs.KuPath

@Suppress("unused")
actual enum class Paths {
  JAVA
  ;

  actual val path: KuPath
    get() = TODO("Not yet implemented")

}