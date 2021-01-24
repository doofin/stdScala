import org.scalajs.linker.interface.StandardConfig
import sbt._
import sbt.Keys._
import sbt.internal.io.Source
// sjs
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
//sjs bundler
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport._
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin
//cross
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject, _}
import scalajscrossproject.ScalaJSCrossPlugin.autoImport._
//packager
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.universal.UniversalPlugin

object build {
  val mScalaVersion = "2.13.3" //2.13.3

  val mScalacOptions = Seq()
//    Seq("-Xcheckinit", "-language:postfixOps", "-Xmigration", "-deprecation")
  val supportedScalaVersions = List("2.12.12", mScalaVersion)

  lazy val sharedPure = (crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Pure) in file("shared"))
    .settings(
      crossScalaVersions := supportedScalaVersions,
      scalaVersion := mScalaVersion,
      cancelable := true,
      libraryDependencies ++= deps.sharedDeps.value
    )

  lazy val js: Project = (project in file("js"))
    .dependsOn(sharedPure.js)
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin) //ScalaJSWeb is for sourcemap
    .settings(
      scalacOptions ++= mScalacOptions,
      libraryDependencies ++= deps.jsDeps.value,
//      npmDependencies in Compile ++= deps.npmDeps,
      webpackBundlingMode := BundlingMode.LibraryAndApplication(),
      scalaJSUseMainModuleInitializer := true,
      scalaJSLinkerConfig := StandardConfig()
        .withSourceMap(false)
        .withModuleKind(ModuleKind.CommonJSModule),
//      https://github.com/scala-js/scala-js/issues/4305
      scalaJSLinkerConfig ~= { _.withESFeatures(_.withAvoidClasses(false)) },
      watchSrc
    )

  lazy val jvm = (project in file("jvm"))
    .dependsOn(sharedPure.jvm)
    .enablePlugins(JavaAppPackaging, UniversalPlugin) // SbtWeb for web assets https://github.com/sbt/sbt-web
    .settings( //log4j-over-slf4j
      resolvers ++= Seq(
        Resolver.bintrayRepo("smarter", "maven"),
        "jitpack" at "https://jitpack.io"
      ),
      libraryDependencies ++= (deps.jvmDepsAll
        .map(_.exclude("org.slf4j", "slf4j-nop")) ++ Seq(
        "org.apache.spark" %% "spark-core" % "3.0.0-X1",
        "com.github.doofin" % "akka-http-session" % "6e3613bc34"
      )),
      //        .map(_.exclude("org.slf4j", "log4j-over-slf4j")),
      watchSrc
    )

  def watchSrc = watchSources := {
    val sources1 = Seq(
      srcDir(baseDirectory.value / "src/main"),
      srcDir((ThisBuild / baseDirectory).value / "shared/src/main")
    )
    sources1
  }

  def srcDir(base: sbt.io.syntax.File) = new Source(
    base,
    sbt.io.AllPassFilter,
    sbt.io.HiddenFileFilter
  )
}
