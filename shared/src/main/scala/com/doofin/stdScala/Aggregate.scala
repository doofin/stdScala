package com.doofin.stdScala

import com.doofin.stdScala._
import macros._

/** aggregated entry, invoked by stdScalaJvm and stdScalaCross */
object Aggregate extends miscUtils with math {

  export dataOps.*
  export stdImplicits.*
  export dataType.*
  export debugAndLog.*
  export systemEnvir.*
}
