package com.maly.scalaland

object RetCalc {
  def futureCapital(interestRate: Double, nbOfMonths: Int,
    netIncome: Int, currentExpenses: Int, initialCapital: Double): Double = {

    val monthlySavings = netIncome - currentExpenses
    (0 until nbOfMonths).foldLeft(initialCapital)(
      (accumulated, _) => accumulated * (1 + interestRate) + monthlySavings)
  }

  def simulatePlan(interestRate: Double, nbOfMonthsSaving: Int, nbOfMonthsInRetirement: Int,
    netIncome: Int, currentExpenses: Int, initialCapital: Double): (Double, Double) = {

    val capitalAtRetirement = futureCapital(interestRate, nbOfMonthsSaving,
      netIncome, currentExpenses, initialCapital)

    val capitalAfterDeath = futureCapital(interestRate, nbOfMonthsInRetirement,
      0, currentExpenses, capitalAtRetirement)

    (capitalAtRetirement, capitalAfterDeath)
  }
}

object RetCalcApp extends App {
}
