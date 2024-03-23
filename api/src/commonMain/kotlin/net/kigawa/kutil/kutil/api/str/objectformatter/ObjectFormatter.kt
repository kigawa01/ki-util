package net.kigawa.kutil.kutil.api.str.objectformatter

interface ObjectFormatter {
  fun format(obj: Any?): String
}