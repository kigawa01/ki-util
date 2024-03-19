package net.kigawa.kutil.kutil.api.reflection

import java.lang.reflect.Field
import java.lang.reflect.Member
import java.lang.reflect.Method
import java.lang.reflect.Modifier

object KutilReflect {
  @JvmStatic
  fun getAllExitFields(clazz: Class<*>): List<Field> {
    return getAllParentClasses(clazz)
      .flatMap {it.declaredFields.asList()}
  }

  @JvmStatic
  fun getAllExitMethod(clazz: Class<*>): List<Method> {
    return getAllParentClasses(clazz)
      .flatMap {it.declaredMethods.asList()}
  }

  @JvmStatic
  fun <T: Annotation> getAllExitAnnotation(clazz: Class<*>, annotationClass: Class<T>): MutableList<T> {
    val list = mutableListOf<T>()
    getAllParentClasses(clazz).forEach {
      val annotation = it.getAnnotation(annotationClass) ?: return@forEach
      list.add(annotation)
    }
    return list
  }
  @JvmStatic
  fun getAllParentClasses(clazz: Class<*>): MutableList<Class<*>> {
    val list = mutableListOf(clazz)
    clazz.superclass?.let {list.addAll(getAllParentClasses(clazz.superclass))}
    clazz.interfaces.forEach {
      list.addAll(getAllParentClasses(it))
    }
    return list
  }
  @JvmStatic
  fun isStatic(member: Member): Boolean {
    return Modifier.isStatic(member.modifiers)
  }

  @JvmStatic
  fun isFinal(member: Member): Boolean {
    return Modifier.isFinal(member.modifiers)
  }

  @JvmStatic
  fun instanceOf(clazz: Class<*>, superClass: Class<*>): Boolean {
    return getAllParentClasses(clazz).contains(superClass)
  }
}