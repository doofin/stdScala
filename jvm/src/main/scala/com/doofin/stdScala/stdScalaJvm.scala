package com.doofin
import com.doofin.stdScala._
import scala.io.AnsiColor._

/** if in jvm, only import this ! otherwise use stdScalaCross */
object stdScalaJvm {
  export Aggregate.*
  export jvmUtils.*
  extension (str: String) {
    def toGreen() = s"${GREEN}$str${RESET}"
    def toRed() = s"${RED}$str${RESET}"
  }

  /** convenient trait for jvm main class */
  trait mainRunnable {
    def main(args: Array[String] = Array()): Unit
  }
}
