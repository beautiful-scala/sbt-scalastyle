enablePlugins(SbtPlugin)
sbtPlugin := true

name := "sbt-scalastyle"
organization := "com.beautiful-scala"
description := "Scalastyle sbt plugin"
homepage := Some(url("https://github.com/beautiful-scala/sbt-scalastyle"))
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/beautiful-scala/sbt-scalastyle"),
    "scm:git:https://github.com/beautiful-scala/sbt-scalastyle.git",
    Some("scm:git:git@github.com:beautiful-scala/sbt-scalastyle.git")
  )
)
developers := List(
  Developer(
    "mwz",
    "Michael Wizner",
    "@mwz",
    url("https://github.com/mwz")
  ),
  Developer(
    "matthewfarwell",
    "Matthew Farwell",
    "@matthewfarwell",
    url("http://www.farwell.co.uk")
  )
)

scalaVersion := "2.12.8"
scalacOptions := Seq(
  "-encoding",
  "UTF-8",
  "-unchecked",
  "-deprecation",
  "-feature",
  "-language:reflectiveCalls",
  "-Yrangepos",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused-import"
)
cancelable in Global := true

libraryDependencies ++= Seq(
  "com.beautiful-scala" %% "scalastyle" % "1.1.1"
)

// scalafix & scalafmt
scalafixDependencies in ThisBuild ++= Seq(
  "com.nequissimus" %% "sort-imports" % "0.3.1"
)
addCommandAlias("fix", "all compile:scalafix test:scalafix")
addCommandAlias("fixCheck", ";compile:scalafix --check ;test:scalafix --check")
scalafmtOnCompile in ThisBuild :=
  sys.env
    .get("CI")
    .forall(_.toLowerCase == "false")

// plugins
addCompilerPlugin(scalafixSemanticdb)
