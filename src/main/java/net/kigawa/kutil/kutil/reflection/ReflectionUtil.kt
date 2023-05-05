@file:Suppress("unused")

package net.kigawa.kutil.kutil.reflection

import java.lang.reflect.*

object ReflectionUtil {
  fun getAllExitFields(clazz: Class<*>): List<Field> {
    return getAllParentClasses(clazz)
      .flatMap {it.declaredFields.asList()}
  }
  
  fun getAllExitMethod(clazz: Class<*>): List<Method> {
    return getAllParentClasses(clazz)
      .flatMap {it.declaredMethods.asList()}
  }
  
  fun <T: Annotation> getAllExitAnnotation(clazz: Class<*>, annotationClass: Class<T>): MutableList<T> {
    val list = mutableListOf<T>()
    getAllParentClasses(clazz).forEach {
      val annotation = it.getAnnotation(annotationClass) ?: return@forEach
      list.add(annotation)
    }
    return list
  }
  
  fun getAllParentClasses(clazz: Class<*>): MutableList<Class<*>> {
    val list = mutableListOf(clazz)
    clazz.superclass?.let {list.addAll(getAllParentClasses(clazz.superclass))}
    clazz.interfaces.forEach {
      list.addAll(getAllParentClasses(it))
    }
    return list
  }
  
  @Suppress("unused")
  fun isStatic(member: Member): Boolean {
    return Modifier.isStatic(member.modifiers)
  }
  
  fun isFinal(member: Member): Boolean {
    return Modifier.isFinal(member.modifiers)
  }
  
  fun instanceOf(clazz: Class<*>, superClass: Class<*>): Boolean {
    return getAllParentClasses(clazz).contains(superClass)
  }
}
