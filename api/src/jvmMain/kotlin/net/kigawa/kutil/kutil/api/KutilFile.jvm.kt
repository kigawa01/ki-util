package net.kigawa.kutil.kutil.api

class KutilFile {
  /**
   * get child file from current
   *
   * @param path child file names
   * @return child file
   */
  fun getRelativeFile(vararg path: String?): java.io.File {
    return getFile(java.nio.file.Paths.get("").toAbsolutePath().toFile(), *path)
  }

  /**
   * get child file
   *
   * @param parent parent file
   * @param path   child file names
   * @return child file
   */
  fun getFile(parent: java.io.File, vararg path: String?): java.io.File {
    var file: java.io.File = parent
    for (name in path) {
      file = java.io.File(file, name)
    }
    return file
  }

  val absolutFile: java.io.File
    /**
     * get current file as absolute
     *
     * @return current dir
     */
    get() = java.nio.file.Paths.get("").toAbsolutePath().toFile()
}