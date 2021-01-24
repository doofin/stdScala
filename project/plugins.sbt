//resolvers ++= Seq("Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/",
//                  "jitpack" at "https://jitpack.io")
//scala js
//https://github.com/circe/circe/issues/1620
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.3.1") //"1.1.1"
//cross
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.0.0")
//bundler
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.20.0") //"0.18.0"
//other
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.7.5") //"1.3.2"
addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1") // for Triggered restart
//addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.1.1") //28
//addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.18.0")
//addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % "1.0.6")
//addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.1" cross CrossVersion.full)
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")
