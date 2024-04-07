package com.maly.scalaland

object SimulatePlanApp extends App {
  println(strMain(args))

  def strMain(args: Array[String]): (String) = {
    val annualInterestRate = args(0).toDouble
    val nbOfYearsSaving = args(1).toInt
    val nbOfYearsInRetirement = args(2).toInt
  
    val params = RetCalcParams(
      nbOfMonthsInRetirement = nbOfYearsInRetirement * 12,
      netIncome = args(3).toInt,
      currentExpenses = args(4).toInt,
      initialCapital = args(5).toDouble)

    val (capitalAtRetirement, capitalAfterDeath) = RetCalc.simulatePlan(
      FixedReturns(annualInterestRate),
      params,
      nbOfYearsSaving * 12)

    s"""
      |Capital after $nbOfYearsSaving years of savings: $capitalAtRetirement
      |Capital aftrt $nbOfYearsInRetirement years in retirement: $capitalAfterDeath
    """.stripMargin
  }
}
