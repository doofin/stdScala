import sbt._
import sbt.Keys._

//cross
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject, _}
import scalajscrossproject.ScalaJSCrossPlugin.autoImport._

object deps {

  /** js deps */
  val jsDeps = Def.setting(
    Seq(
    )
  )

  /** shared deps */
  val sharedDeps = Def.setting(
    Seq(
      "io.suzaku" %%% "boopickle" % "1.4.0", // "1.3.3",
      "com.lihaoyi" %%% "pprint" % "0.8.1" //"0.6.0",
      // "org.latestbit" %%% "circe-tagged-adt-codec" % "0.10.1" // "0.9.1",
    )
  )

  private val catsV = "2.1.1" //1.4.0

  val jvmDeps = Seq("org.scalameta" %% "munit" % "0.7.29" % Test)

}
