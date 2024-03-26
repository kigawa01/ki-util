package net.kigawa.kutil.kutil.api

import net.kigawa.kutil.kutil.api.io.fs.KuPath

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect enum class Paths {
  JAVA
  ;

  val path: KuPath
}