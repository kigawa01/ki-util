package net.kigawa.kutil.kutil.api.validator.str

import net.kigawa.kutil.kutil.api.validator.Validator

class StrValidator<ORIGINAL : Any?, FROM : Any?>(parent: Validator<ORIGINAL, *, FROM>) :
  AbstractStrValidator<ORIGINAL, FROM>(parent) {

  override fun validateTask(from: FROM): String {
    return from.toString()
  }
}