package net.kigawa.kutil.kutil.api.io.fs

import net.kigawa.kutil.kutil.api.list.KutilList

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KuPath actual constructor(strPath: String) {
  actual companion object {
    actual val separator: String = "/"
    fun listToPathStr(list: List<String>, isRoot: Boolean): String {
      val prefix = if (isRoot) separator else ""
      return list
        .joinToString(separator = separator, prefix = prefix)
    }
  }

  private val path: List<String> = strPath.split(separator)
  private val isRoot = strPath.startsWith(separator)

  actual fun join(child: String): KuPath {
    return KutilList.connectList(path, child.split(separator))
      .let { listToPathStr(it, isRoot) }
      .let { KuPath(it) }
  }

  actual fun join(child: KuPath): KuPath {
    TODO("Not yet implemented")
  }

  actual fun strPath(): String {
    TODO("Not yet implemented")
  }

  actual fun toAbsolute(): KuPath {
    TODO("Not yet implemented")
  }

  actual fun parent(): KuPath {
    TODO("Not yet implemented")
  }

  actual fun createDirOrGet(): KuDirectory {
    TODO("Not yet implemented")
  }

  actual fun toFile(): KuFile {
    TODO("Not yet implemented")
  }

  actual fun isExists(): Boolean {
    TODO("Not yet implemented")
  }

  actual fun removeIfExists() {
  }

}