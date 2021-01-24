import sbt._
import sbt.Keys._

//cross
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject, _}
import scalajscrossproject.ScalaJSCrossPlugin.autoImport._

object deps {

  /**js deps*/
  val jsDeps = Def.setting(
    Seq(
      )
  )

  /**shared deps*/
  val sharedDeps = Def.setting(
    Seq(
      "io.suzaku" %%% "boopickle" % "1.3.3",
      "com.lihaoyi" %%% "pprint" % "0.6.0",
      "com.lihaoyi" %%% "autowire" % "0.3.2",
      "org.latestbit" %%% "circe-tagged-adt-codec" % "0.9.1",
      "com.chuusai" %% "shapeless" % "2.3.3"
    )
  )

  private val catsV = "2.1.1" //1.4.0
  private val jvmLogging = Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.slf4j" % "slf4j-api" % "1.7.30"
  )

  val jvmDeps = Seq(
    "org.typelevel" %% "cats-core" % catsV,
    "org.typelevel" %% "cats-free" % catsV,
    "com.github.julien-truffaut" %% "monocle-core" % "2.0.5",
    "com.github.julien-truffaut" %% "monocle-generic" % "2.0.5",
    "com.github.julien-truffaut" %% "monocle-macro" % "2.0.5",
    "org.scalatest" %% "scalatest" % "3.0.8" % Test
//    "org.scala-lang" % "scala-compiler" % "2.13.4"
  ) ++ jvmLogging

}
