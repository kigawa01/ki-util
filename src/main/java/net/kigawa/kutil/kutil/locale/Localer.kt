@file:Suppress("unused")

package net.kigawa.kutil.kutil.locale

import java.util.*

class Localer(private var defaultLanguage: String, private var localerResource: LocalerResources) {
  fun getLocaleData(key: String, locale: String) = localerResource.getLocaleData(key, locale)
  fun getLocaleData(key: String) = getLocaleData(key, Locale.getDefault().language)
  fun getDefaultLocaleData(key: String) = getLocaleData(key, defaultLanguage)
  
  fun use(key: String) = use(key, null)
  fun use(key: String, defaultMessage: String?): String {
    defaultMessage?.let {on(defaultLanguage, key, it)}
    return getLocaleData(key)?.message ?: getDefaultLocaleData(key)?.message ?: defaultMessage ?: "message not found"
  }
  
  fun on(language: String, key: String, message: String): Localer {
    localerResource.setData(LocaleData(language, key, message))
    return this
  }
}