package net.kigawa.kutil.kutil.locale

import net.kigawa.kutil.kutil.list.KList
import net.kigawa.kutil.kutil.list.firstOrNullMap

class LocalerResources(vararg resource: LocalerResource) {
  private val resources = KList.create(resource.toMutableSet())
  
  fun getLocales(key: String? = null, language: String? = null): Set<LocaleData> {
    return resources
      .reversed()
      .flatMap {it.getLocales(key, language)}
      .toMutableSet()
  }
  
  fun getLocaleData(key: String, language: String): LocaleData? {
    return resources
      .firstOrNullMap {it.getLocale(key, language)}
  }
  
  fun setData(localeData: LocaleData): Boolean {
    return resources
      .firstOrNull {it.setLocale(localeData)} == null
  }
}