package com.doofin
import com.doofin.stdScala._
import scala.io.AnsiColor._

object stdScalaJvm
    extends miscUtils
    // with types
    with dataOps
    with math
    // with stdSerializers
    with stdImplicits
    with jvmUtils {

  val strGreen = (str: String) => s"${GREEN}$str${RESET}"
  val strRed = (str: String) => s"${RED}$str${RESET}"
}
