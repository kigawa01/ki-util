package net.kigawa.kutil.kutil.api.validator.str

import net.kigawa.kutil.kutil.api.validator.AbstractValidator
import net.kigawa.kutil.kutil.api.validator.Validator

abstract class AbstractStrValidator<ORIGINAL : Any?, FROM : Any?>(parent: Validator<ORIGINAL, *, FROM>) :
  AbstractValidator<ORIGINAL, FROM, String>(parent) {
}