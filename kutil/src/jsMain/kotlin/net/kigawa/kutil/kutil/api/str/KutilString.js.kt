package net.kigawa.kutil.kutil.api.str

/**
 * utilities about string
 */
@Suppress("unused", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object KutilString {
  /**
   * check is int
   *
   * @param str to test
   * @return return true when str is int
   */
  actual fun isInt(str: String): Boolean {
    return str.matches("[+-]?\\d*(\\.\\d+)?".toRegex())
  }


}