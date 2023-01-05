package com.doofin
import com.doofin.stdScala._
import scala.io.AnsiColor._

object stdScalaJvm
    extends miscUtils
    with dataOps
    with math
    with stdImplicits
    with jvmUtils {

  val strGreen = (str: String) => s"${GREEN}$str${RESET}"
  val strRed = (str: String) => s"${RED}$str${RESET}"

  /* extension (str: String) {
    def toGreen() = s"${GREEN}$str${RESET}"
    def toRed() = s"${RED}$str${RESET}"
  } */
}
