package net.kigawa.kutil.kutil.locale

interface LocalerResource {
  fun getLocales(key: String? = null, language: String? = null): List<LocaleData>
  fun getLocale(key: String, language: String): LocaleData? {
    return getLocales(key, language).firstOrNull()
  }
  
  fun setLocale(localeData: LocaleData): Boolean
}