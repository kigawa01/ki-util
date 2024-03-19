package net.kigawa.kutil.kutil.api

import net.kigawa.kutil.kutil.api.io.fs.KuPath

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual enum class Paths(
  actual val path: KuPath,
) {
  JAVA(KuPath(System.getProperty("java.home")).join("bin/java"))
  ;

}