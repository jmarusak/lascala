package com.maly.scalaland

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalactic.{Equality, TolerantNumerics, TypeCheckedTripleEquals}

class RetCalcSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals {

  implicit val doubleEquality: Equality[Double] =
    TolerantNumerics.tolerantDoubleEquality(0.0001)

  "RetCalc.futureCapital" should {
    "calculate future value of savings in n-months" in {
      val actual = RetCalc.futureCapital(
        interestRate = 0.04 / 12,
        nbOfMonths = 25 * 12,
        netIncome = 3000,
        currentExpenses = 2000,
        initialCapital = 10000)
      val expected = 541267.1990

      actual should ===(expected)
    }
  }

  "RetCalc.simulatePlan" should {
    "calculate capital at retirement and at death" in {
      val (capitalAtRetirement, capitalAfterDeath) = RetCalc.simulatePlan(
        interestRate = 0.04 / 12,
        nbOfMonthsSaving = 25 * 12,
        nbOfMonthsInRetirement = 40 * 12,
        netIncome = 3000,
        currentExpenses = 2000,
        initialCapital = 10000)

      capitalAtRetirement should ===(541267.1990)
      capitalAfterDeath should ===(309867.5316)
    }
  }
}
