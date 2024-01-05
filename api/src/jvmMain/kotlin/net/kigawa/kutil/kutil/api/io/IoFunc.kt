package net.kigawa.mcsm.util.io

import kotlinx.coroutines.withContext
import net.kigawa.mcsm.util.concurrent.DefaultCoroutines
import java.io.BufferedReader
import kotlin.coroutines.CoroutineContext

suspend inline fun BufferedReader.forEachLineSuspend(
  context: CoroutineContext = DefaultCoroutines.ioContext,
  block: (String) -> Unit,
) {
  while (true) {
    val line: String = withContext(context) {
      readLine()
    } ?: return
    block(line)
  }
}