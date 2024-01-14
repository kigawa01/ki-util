package net.kigawa.mcsm.util.net

import kotlinx.coroutines.channels.Channel
import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.mcsm.util.io.KuPath
import net.kigawa.mcsm.util.io.SuspendCloseable
import net.kigawa.kutil.kutil.api.logger.KuLogger

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class SocketServer(
  path: KuPath,
  logger: KuLogger?,
  coroutines: Coroutines,
) : SuspendCloseable {
  fun bind(): Channel<SocketConnection>
}