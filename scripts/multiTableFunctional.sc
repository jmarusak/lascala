def makeRowSeq(row: Int): IndexedSeq[String] =
  for (col <- 1 to 10) yield {
    val product = (row * col).toString
    val padding = " " * (4 - product.length)
    padding + product
  }

def makeRow(row: Int): String = makeRowSeq(row).mkString

def multiTable(): String = {
  val tableSeq =
    for (row <- 1 to 10)
      yield makeRow(row)

  tableSeq.mkString("\n")
}

print(multiTable)
