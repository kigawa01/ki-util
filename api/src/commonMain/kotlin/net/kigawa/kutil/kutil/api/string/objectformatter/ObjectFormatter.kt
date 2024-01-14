package net.kigawa.kutil.kutil.api.string.objectformatter

interface ObjectFormatter {
  fun format(obj: Any?): String
}