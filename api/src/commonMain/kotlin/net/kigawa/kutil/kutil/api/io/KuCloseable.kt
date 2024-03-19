package net.kigawa.kutil.kutil.api.io

@OptIn(ExperimentalStdlibApi::class)
interface KuCloseable : AutoCloseable {
  override fun close()
}
