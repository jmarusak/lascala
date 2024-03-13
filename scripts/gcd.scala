def gcdLoop(a: Int, b: Int): Int =  {
  var x = a
  var y = b

  while(x > 0) {
    val temp = x
    x = y % x
    y = temp 
  }
  y
}

def gcd(a: Int, b: Int): Int =
  if (a == 0) b else gcd(b % a, a)
