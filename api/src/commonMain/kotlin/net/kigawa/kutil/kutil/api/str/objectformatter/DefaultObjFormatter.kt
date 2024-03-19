package net.kigawa.kutil.kutil.api.str.objectformatter

class DefaultObjFormatter : ObjectFormatter {
  override fun format(obj: Any?): String {
    return when (obj) {
      is String -> obj
      is Array<*> -> obj.joinToString(
        separator = ", ",
        prefix = "Array[",
        postfix = "]",
        transform = { format(obj) }
      )
      else -> obj.toString()
    }
  }
}