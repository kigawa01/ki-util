package net.kigawa.kutil.kutil.api.validator

interface Validator<ORIGINAL : Any?, FROM : Any?, TO : Any?> {
  fun validate(value: ORIGINAL): TO

}