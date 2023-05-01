package net.kigawa.kutil.kutil.locale

import java.io.File
import java.io.FileFilter
import java.util.*

class DirLocalerResource(private val dir: File): LocalerResource {
  private val locales = mutableSetOf<LocaleData>()
  private val changed = mutableSetOf<LocaleData>()
  
  init {
    load()
  }
  
  fun load(): Properties {
    val properties = Properties()
    dir
      .listFiles(FileFilter {it.name.endsWith(".properties")})
      ?.forEach {file->
        val language = file.name.replace(".properties", "")
        
        file.inputStream().use(properties::load)
        properties.forEach {
          LocaleData(language, it.key.toString(), it.value.toString())
            .also(locales::add)
        }
      }
    return properties
  }
  
  fun save() {
    val property = load()
  }
  
  fun reload() {
    save()
    load()
  }
  
  override fun getLocales(key: String?, language: String?): List<LocaleData> {
    return locales
      .filter {it.key == key}
      .filter {it.language == language}
  }
  
  override fun setLocale(localeData: LocaleData): Boolean {
    TODO("Not yet implemented")
  }
}