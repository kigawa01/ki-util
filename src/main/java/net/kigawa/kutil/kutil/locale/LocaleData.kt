package net.kigawa.kutil.kutil.locale

data class LocaleData(
  val language: String,
  val key: String,
  val message: String,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is LocaleData) return false
    
    if (language != other.language) return false
    if (key != other.key) return false
    return message == other.message
  }
  
  override fun hashCode(): Int {
    var result = language.hashCode()
    result = 31 * result + key.hashCode()
    result = 31 * result + message.hashCode()
    return result
  }
}