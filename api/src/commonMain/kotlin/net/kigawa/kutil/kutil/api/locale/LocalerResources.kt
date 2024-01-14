@file:Suppress("unused")

package net.kigawa.kutil.kutil.api.locale

import net.kigawa.kutil.kutil.api.list.firstOrNullMap

class LocalerResources(vararg resource: LocalerResource) {
  private val resources = listOf(*resource)

  fun getLocales(key: String? = null, language: String? = null): Set<LocaleData> {
    return resources
      .reversed()
      .flatMap { it.getLocales(key, language) }
      .toMutableSet()
  }

  fun getLocaleData(key: String, language: String): LocaleData? {
    return resources
      .firstOrNullMap { it.getLocale(key, language) }
  }

  fun setData(localeData: LocaleData): Boolean {
    return resources
      .firstOrNull { it.setLocale(localeData) } == null
  }
}