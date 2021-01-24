import sbt._
import Keys._
import build._

object globalSettings extends AutoPlugin {
  override def trigger = allRequirements
//  override def extraProjects = Seq(build.sharedPure,build.jvm,build.js)
  override def globalSettings = {
    val cmdAlias = Seq(
      ("jsdev", ";project js; ~js/fastOptJS::webpack"),
      ("jvmdev", ";project jvm;~reStart"),
      ("r", "jvm/reStart"),
      ("jrun", "jvm/reStart"),
      ("jstop", "jvm/reStop"),
      ("run", "jvm/reStart"),
      ("csl", "jvm/console")
    ).flatMap(x => addCommandAlias(x._1, x._2))

    val misc = Seq(
      scalaVersion := build.mScalaVersion,
      resolvers ++= Seq(
        Resolver.mavenLocal
        //        "jitpack" at "https://jitpack.io"
      )
    )

    cmdAlias ++ misc
  }
}
