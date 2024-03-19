package net.kigawa.kutil.kutil.api

import net.kigawa.kutil.kutil.api.io.fs.KuPath

@Suppress("unused")
actual enum class Paths(
  actual val path: KuPath,
) {
  JAVA(KuPath(System.getProperty("java.home")).join("bin/java"))
  ;

}