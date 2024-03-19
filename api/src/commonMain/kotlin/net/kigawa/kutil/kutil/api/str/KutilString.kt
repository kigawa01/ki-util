package net.kigawa.kutil.kutil.api.str

/**
 * utilities about string
 */
@Suppress("unused")
object KutilString {
  /**
   * check is int
   *
   * @param str to test
   * @return return true when str is int
   */
  fun isInt(str: String): Boolean {
    return str.matches("[+-]?\\d*(\\.\\d+)?".toRegex())
  }

}
