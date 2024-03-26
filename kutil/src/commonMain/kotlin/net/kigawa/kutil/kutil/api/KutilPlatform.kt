package net.kigawa.kutil.kutil.api

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object KutilPlatform {
  fun getSystemProperty(name: String): String
  fun getEnv(name: String): String?
}