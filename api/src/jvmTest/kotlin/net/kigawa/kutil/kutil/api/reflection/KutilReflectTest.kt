package net.kigawa.kutil.kutil.api.reflection

import kotlin.test.Test

class KutilReflectTest {
  @Test
  fun getAllParentClasses() {
    KutilReflect.getAllParentClasses(javaClass)
  }
}