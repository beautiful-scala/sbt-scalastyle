version := "0.1"

scalaVersion := "2.12.8"

val containsMessage = taskKey[Boolean]("contains message")

containsMessage := {
  val expected = Seq("hello-2.12.scala", "hello-java.scala", "hello-scala.scala")

  val filename = "target/scalastyle-test-result.xml"
  val lines = sbt.IO.readLines(file(filename))

  expected.foreach { search =>
    val contains = lines.exists(s => s.contains(search))
    if (!contains) {
      sys.error("Could not find " + search + " in " + filename)
    }
  }
  true
}
