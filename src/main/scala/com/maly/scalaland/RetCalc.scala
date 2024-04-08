package com.maly.scalaland

case class RetCalcParams(
  nbOfMonthsInRetirement: Int,
  netIncome: Int,
  currentExpenses: Int,
  initialCapital: Double)

object RetCalc {
  def futureCapital(returns: Returns, nbOfMonths: Int,
    netIncome: Int, currentExpenses: Int, initialCapital: Double): Double = {

    val monthlySavings = netIncome - currentExpenses
    (0 until nbOfMonths).foldLeft(initialCapital)(
      (accumulated, month) => accumulated * (1 + Returns.monthlyRate(returns, month)) + monthlySavings)
  }

  def simulatePlan(returns: Returns, params: RetCalcParams, nbOfMonthsSaving: Int): (Double, Double) = {
    import params._
    val capitalAtRetirement = futureCapital(returns, nbOfMonthsSaving,
      netIncome, currentExpenses, initialCapital)
    val capitalAfterDeath = futureCapital(returns, nbOfMonthsInRetirement,
      0, currentExpenses, capitalAtRetirement)

    (capitalAtRetirement, capitalAfterDeath)
  }

  def nbOfMonthsSaving(returns: Returns, params: RetCalcParams): Option[Int] = {
    def loop(months: Int): Int = {
      val (capitalAtRetirement, capitalAfterDeath) = simulatePlan(returns, params, months) 

      if (capitalAfterDeath > 0.0) months else loop(months + 1)
    }
    
    if (params.netIncome > params.currentExpenses) Some(loop(0)) else None
  }
}
