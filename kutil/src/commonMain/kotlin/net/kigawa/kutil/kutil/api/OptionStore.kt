package net.kigawa.kutil.kutil.api

@Suppress("unused")
class OptionStore {
  private val options = mutableMapOf<Option, String>()

  fun add(option: Option, value: String) {
    options[option] = value
  }


  fun get(option: Option): String = options[option]
    ?: KutilPlatform.getEnv(option.name)
    ?: option.defaultValue

}