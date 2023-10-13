package net.kigawa.kutil.kutil.api.manager

abstract class ManagedEntry<SELF: ManagedEntry<SELF, PARENT>, PARENT: RemoveAble?>(
  @Suppress("MemberVisibilityCanBePrivate")
  protected val manager: Manager<SELF>,
  override val parentField: PARENT,
): RemoveAble(parentField) {
  
  override fun remove() {
    super.remove()
    @Suppress("UNCHECKED_CAST")
    manager.remove(this as SELF)
  }
}