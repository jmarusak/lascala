def multiTable() = {
  var i = 1

  while (i <= 10) {
    var j = 1
    while (j <= 10) {
      val product = (i * j).toString

      var len = product.length
      while(len < 4) {
        print(" ")
        len += 1
      }

      print(product)
      j += 1
    }

    println()
    i += 1
  }
}

multiTable()
