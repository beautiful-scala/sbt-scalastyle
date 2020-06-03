enablePlugins(SbtPlugin)
enablePlugins(BuildInfoPlugin)
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

scalaVersion := "2.12.11"
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

libraryDependencies += "com.beautiful-scala" %% "scalastyle" % "1.4.0"

// build info
buildInfoKeys := Seq[BuildInfoKey](organization, name, version, scalaVersion, sbtVersion)
buildInfoPackage := "org.scalastyle.sbt"

// scalafix & scalafmt
scalafixDependencies in ThisBuild ++= Seq(
  "com.nequissimus" %% "sort-imports" % "0.5.2"
)
addCommandAlias("fix", "all compile:scalafix test:scalafix; fixImports")
addCommandAlias("fixImports", "compile:scalafix SortImports; test:scalafix SortImports")
addCommandAlias("fixCheck", "compile:scalafix --check; test:scalafix --check; fixCheckImports")
addCommandAlias("fixCheckImports", "compile:scalafix --check SortImports; test:scalafix --check SortImports")
scalafmtOnCompile in ThisBuild :=
  sys.env
    .get("CI")
    .forall(_.toLowerCase == "false")

// plugins
addCompilerPlugin(scalafixSemanticdb)
