package net.kigawa.kutil.kutil.api.str

import java.util.*
import java.util.function.Function

@Suppress("unused")
actual object KutilString {

  /**
   * insert symbol between string array
   *
   * @param sb      base string buffer
   * @param symbol  string symbol
   * @param strings base string array
   * @return append string buffer
   */
  fun insertSymbol(sb: StringBuffer, symbol: String?, strings: Array<String?>?): StringBuffer {
    return insertSymbol(sb, symbol, Arrays.stream<String>(strings).iterator())
  }

  /**
   * insert symbol between string Iterable
   *
   * @param sb             base string buffer
   * @param symbol         string symbol
   * @param stringIterable base string iterable
   * @return append string buffer
   */
  fun insertSymbol(
    sb: StringBuffer, symbol: String?, stringIterable: Iterable<String>,
  ): StringBuffer {
    return insertSymbol(sb, symbol, stringIterable.iterator())
  }

  /**
   * insert symbol between string iterator
   *
   * @param sb       base string buffer
   * @param symbol   string symbol
   * @param iterator base string iterator
   * @return append string buffer
   */
  fun insertSymbol(sb: StringBuffer, symbol: String?, iterator: Iterator<String>): StringBuffer {
    return insertSymbol(sb, symbol, iterator) { s: String? -> s }
  }

  /**
   * insert symbol between string that created by function
   *
   * @param sb       base string buffer
   * @param symbol   string symbol
   * @param array    base array
   * @param function to create function
   * @param <T>      array type
   * @return append string buffer
  </T> */
  fun <T> insertSymbol(
    sb: StringBuffer, symbol: String?, array: Array<T>?, function: Function<T, String?>,
  ): StringBuffer {
    return insertSymbol(sb, symbol, Arrays.stream(array).iterator(), function)
  }

  /**
   * insert symbol between string that created by function
   *
   * @param sb       base string buffer
   * @param symbol   string symbol
   * @param iterable base iterable
   * @param function to create function
   * @param <T>      iterable type
   * @return append string buffer
  </T> */
  fun <T> insertSymbol(
    sb: StringBuffer, symbol: String?, iterable: Iterable<T>,
    function: Function<T, String?>,
  ): StringBuffer {
    return insertSymbol(sb, symbol, iterable.iterator(), function)
  }

  /**
   * insert symbol between string that created by function
   *
   * @param sb       base string buffer
   * @param symbol   string symbol
   * @param iterator base iterator
   * @param function to create function
   * @param <T>      iterator type
   * @return append string buffer
  </T> */
  fun <T> insertSymbol(
    sb: StringBuffer, symbol: String?, iterator: Iterator<T>,
    function: Function<T, String?>,
  ): StringBuffer {
    while (iterator.hasNext()) {
      sb.append(function.apply(iterator.next()))
      if (iterator.hasNext()) sb.append(symbol)
    }
    return sb
  }

  /**
   * insert symbol between string that created by function
   *
   * @param symbol         string symbol
   * @param stringIterable base string iterable
   * @return created string
   */
  fun insertSymbol(symbol: String?, stringIterable: Iterable<String>): String {
    return insertSymbol(StringBuffer(), symbol, stringIterable.iterator()).toString()
  }

  actual fun isInt(str: String): Boolean {
    return str.matches("[+-]?\\d*(\\.\\d+)?".toRegex())
  }

}