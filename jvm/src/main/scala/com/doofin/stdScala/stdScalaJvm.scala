package com.doofin
import com.doofin.stdScala._
import scala.io.AnsiColor._

/** only import this if in jvm! otherwise use stdScalaCross */
object stdScalaJvm extends Agg with jvmUtils {

  extension (str: String) {
    def toGreen() = s"${GREEN}$str${RESET}"
    def toRed() = s"${RED}$str${RESET}"
  }
}
