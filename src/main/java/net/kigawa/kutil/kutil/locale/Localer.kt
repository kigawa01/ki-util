@file:Suppress("unused")

package net.kigawa.kutil.kutil.locale

import java.util.*

class Localer(private var defaultLocale: Locale) {
  private val locales = mutableSetOf<LocaleData>()
  fun getLocaleData(key: String, locale: Locale): LocaleData? {
    return locales
      .filter {it.key == key}
      .firstOrNull {it.locale == locale}
  }
  
  fun getLocaleData(key: String) = getLocaleData(key, Locale.getDefault())
  
  fun getDefaultLocaleData(key: String) = getLocaleData(key, defaultLocale)
  
  fun use(key: String) = use(key, null)
  
  fun use(key: String, defaultMessage: String?): String {
    defaultMessage?.let {on(defaultLocale, key, it)}
    return getLocaleData(key)?.message ?: getDefaultLocaleData(key)?.message ?: defaultMessage ?: "message not found"
  }
  
  fun on(locale: Locale, key: String, message: String): Localer {
    locales.add(LocaleData(locale, key, message))
    return this
  }
}