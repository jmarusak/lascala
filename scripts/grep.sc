val files = (new java.io.File(".")).listFiles()

def lines(file: java.io.File) =
  scala.io.Source.fromFile(file).getLines().toArray

for (
  file <- files
  if file.isFile()
  if file.getName.endsWith(".scala");
  line <- lines(file)
  if line.trim.matches(".*var.*")
) println(s"${file}: ${line}")
