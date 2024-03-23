package net.kigawa.kutil.kutil.api.io

import kotlinx.coroutines.withContext
import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import java.io.BufferedReader
import kotlin.coroutines.CoroutineContext

suspend inline fun BufferedReader.forEachLineSuspend(
  context: CoroutineContext = Coroutines.defaultIoContext,
  block: (String) -> Unit,
) {
  while (true) {
    val line: String = withContext(context) {
      readLine()
    } ?: return
    block(line)
  }
}