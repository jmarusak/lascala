import Console.{GREEN, RED, RESET}

object PrimeTest {

  def isPrime(): Unit = {
    val candidate = io.StdIn.readInt()
    val prime = (2 to candidate - 1).forall(candidate % _ != 0)

    if (prime)
      Console.println(s"${RESET}${GREEN}yes${RESET}")
    else
      Console.println(s"${RESET}${RED}no${RESET}")
  }

  def main(args: Array[String]): Unit =
    isPrime()
}
