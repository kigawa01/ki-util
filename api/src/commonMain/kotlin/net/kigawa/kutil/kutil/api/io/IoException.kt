package net.kigawa.kutil.kutil.api.io

open class IoException(message: String?) : Exception(message) {
}

expect fun ioException(message: String?): IoException