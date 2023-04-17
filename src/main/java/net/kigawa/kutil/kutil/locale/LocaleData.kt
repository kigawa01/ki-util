package net.kigawa.kutil.kutil.locale

import java.util.*

data class LocaleData(
  val locale: Locale,
  val key: String,
  val message: String,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is LocaleData) return false
    
    if (locale != other.locale) return false
    if (key != other.key) return false
    return message == other.message
  }
  
  override fun hashCode(): Int {
    var result = locale.hashCode()
    result = 31 * result + key.hashCode()
    result = 31 * result + message.hashCode()
    return result
  }
}