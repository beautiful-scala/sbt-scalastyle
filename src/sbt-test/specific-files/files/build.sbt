version := "0.1"
 
scalaVersion := "2.12.8"
 
val containsMessage = taskKey[Boolean]("contains message")

containsMessage := {
    val search = "File length exceeds"
    val filename = "target/scalastyle-result.xml"
    val lines = sbt.IO.readLines(file(filename))
    val contains = lines.find(s => s.contains(search)).isDefined
    if (contains) {
        sys.error("Found " + search + " in " + filename)
    }
    contains
}
