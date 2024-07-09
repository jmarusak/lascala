package retirement 

case class CalculatorParams(
  nbOfMonthsInRetirement: Int,
  netIncome: Int,
  currentExpenses: Int,
  initialCapital: Double)

object Calculator {
  def futureCapital(returns: Returns, nbOfMonths: Int,
    netIncome: Int, currentExpenses: Int, initialCapital: Double): Double = {

    val monthlySavings = netIncome - currentExpenses
    (0 until nbOfMonths).foldLeft(initialCapital)(
      (accumulated, month) => accumulated * (1 + Returns.monthlyRate(returns, month)) + monthlySavings)
  }

  def simulatePlan(returns: Returns, params: CalculatorParams, nbOfMonthsSaving: Int): (Double, Double) = {
    import params._
    val capitalAtRetirement = futureCapital(returns, nbOfMonthsSaving,
      netIncome, currentExpenses, initialCapital)
    val capitalAfterDeath = futureCapital(returns, nbOfMonthsInRetirement,
      0, currentExpenses, capitalAtRetirement)

    (capitalAtRetirement, capitalAfterDeath)
  }

  def nbOfMonthsSaving(returns: Returns, params: CalculatorParams): Option[Int] = {
    def loop(months: Int): Int = {
      val (capitalAtRetirement, capitalAfterDeath) = simulatePlan(returns, params, months) 

      if (capitalAfterDeath > 0.0) months else loop(months + 1)
    }
    
    if (params.netIncome > params.currentExpenses) Some(loop(0)) else None
  }
}
