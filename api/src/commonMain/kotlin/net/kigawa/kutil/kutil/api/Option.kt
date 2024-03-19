package net.kigawa.kutil.kutil.api

  @Suppress("unused")
interface Option {
  val name: String
  val optName: String
  val shortName: String?
  val description: String
  val defaultValue: String
}