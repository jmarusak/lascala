package com.maly.scalaland

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalactic.{Equality, TolerantNumerics, TypeCheckedTripleEquals}

class RetCalcSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals {

  implicit val doubleEquality: Equality[Double] =
    TolerantNumerics.tolerantDoubleEquality(0.0001)
  
  val params = RetCalcParams(
    nbOfMonthsInRetirement = 40 * 12,
    netIncome = 3000,
    currentExpenses = 2000,
    initialCapital = 10000)

  "RetCalc.futureCapital" should {
    "calculate future value of savings in n-months" in {
      val actual = RetCalc.futureCapital(
        FixedReturns(0.04),
        nbOfMonths = 25 * 12,
        params.netIncome,
        params.currentExpenses,
        params.initialCapital)

      val expected = 541267.1990
      actual should ===(expected)
    }
  }

  "RetCalc.simulatePlan" should {
    "calculate capital at retirement and at death" in {
      val (capitalAtRetirement, capitalAfterDeath) = RetCalc.simulatePlan(
        FixedReturns(0.04),
        params,
        nbOfMonthsSaving = 25 * 12)

      capitalAtRetirement should ===(541267.1990)
      capitalAfterDeath should ===(309867.5316)
    }
  }

  "RetCalc.nbOfMonthsSaving" should {
    "calculate how long I need to save before retiring" in {
      val actual = RetCalc.nbOfMonthsSaving(
        FixedReturns(0.04),
        params)
      
      val expected = 23 * 12 + 1
      actual should ===(expected)
    }
  }
}
