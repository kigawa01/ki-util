package net.kigawa.kutil.kutil.api.validator

class EmptyValidator<T : Any?> : Validator<T, T, T> {
  override fun validate(value: T): T {
    return value
  }
}