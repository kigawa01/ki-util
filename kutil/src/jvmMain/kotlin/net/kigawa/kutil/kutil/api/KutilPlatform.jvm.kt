package net.kigawa.kutil.kutil.api

actual object KutilPlatform {
  actual fun getSystemProperty(name: String): String = System.getProperty(name)
  actual fun getEnv(name: String): String? = System.getenv(name)
}