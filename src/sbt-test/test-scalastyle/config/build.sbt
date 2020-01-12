version := "0.1"

scalaVersion := "2.12.8"

scalastyleConfig in Test := file("scalastyle-test-config.xml")

val containsMessage = taskKey[Boolean]("contains message")

containsMessage := {
  val search = "File length exceeds"
  val filename = "target/scalastyle-test-result.xml"
  val lines = sbt.IO.readLines(file(filename))
  val contains = lines.find(s => s.contains(search)).isDefined
  if (!contains) {
    sys.error("Could not find " + search + " in " + filename)
  }
  contains
}
