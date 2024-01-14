package net.kigawa.kutil.kutil.api.io

import net.kigawa.mcsm.util.io.WriterIo

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object DefaultIo {
  actual val error: WriterIo<String> = FunctionWriterIo { console.error(it) }
  actual val out: WriterIo<String> = FunctionWriterIo { console.log(it) }
}