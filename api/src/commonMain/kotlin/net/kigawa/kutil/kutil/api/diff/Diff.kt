package net.kigawa.kutil.kutil.api.diff
data class Diff<T>(val added: T, val removed: T)