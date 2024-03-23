package net.kigawa.kutil.kutil.api.net

import net.kigawa.kutil.kutil.api.io.fs.KuPath

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Url(
  strUrl: String,
) {
  suspend fun download(to: KuPath)
}