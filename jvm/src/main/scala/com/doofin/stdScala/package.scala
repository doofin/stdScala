package com.doofin
import scala.io.AnsiColor._

package object stdScalaJvm
    extends miscUtils
    with types
    with dataOps
    with math
    with stdSerializers
    with stdImplicits
    with jvmUtils {
      
  val strGreen = (str: String) => s"${GREEN}$str${RESET}"
  val strRed = (str: String) => s"${RED}$str${RESET}"
    }
