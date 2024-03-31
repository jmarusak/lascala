abstract class CurrencyZone {
  type Currency <: CurrencyBase
  def make(x: Int): Currency

  abstract class CurrencyBase {
    val amount: Int
    def designation: String
    override def toString: String = s"$amount $designation"
    def + (that: Currency): Currency = make(this.amount + that.amount)
    def * (x: Double): Currency = make((this.amount * x).toInt)
    def from(other: CurrencyZone#CurrencyBase): Currency = {
      make(math.round(other.amount.toFloat * Converter.exchangeRate(other.designation)(this.designation).toFloat))
    }
  }
}

object US extends CurrencyZone {
  abstract class Dollar extends CurrencyBase {
    def designation = "USD"
  }

  type Currency = Dollar
  def make(x: Int) = new Dollar {
    val amount = x
  }

  val Dollar = make(1)
}

object Europe extends CurrencyZone {
  abstract class Euro extends CurrencyBase {
    def designation = "EUR"
  }

  type Currency = Euro
  def make(x: Int) = new Euro {
    val amount = x
  }

  val Euro = make(1)
}

object Converter {
  val exchangeRate = Map(
    "USD" -> Map("USD" -> 1.00, "EUR" -> 0.75),
    "EUR" -> Map("USD" -> 1.30, "EUR" -> 1.00)
  )
}

val price = US.make(100)
val tax = price * 0.13
val total = price + tax
println((price, tax, total))

val buyUSD = US.Dollar from Europe.Euro * 100
println(buyUSD)

val sellUSD = Europe.Euro from US.Dollar * 100
println(sellUSD)
