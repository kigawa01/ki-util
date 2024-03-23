package net.kigawa.kutil.kutil.api.str

/**
 * utilities about string
 */
@Suppress("unused", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object KutilString {
  /**
   * check is int
   *
   * @param str to test
   * @return return true when str is int
   */
 fun isInt(str: String): Boolean
}
