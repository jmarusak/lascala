package retirement 

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalactic.{Equality, TolerantNumerics, TypeCheckedTripleEquals}

class CalculatorSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals {

  implicit val doubleEquality: Equality[Double] =
    TolerantNumerics.tolerantDoubleEquality(0.0001)
  
  val params = CalculatorParams(
    nbOfMonthsInRetirement = 40 * 12,
    netIncome = 3000,
    currentExpenses = 2000,
    initialCapital = 10000)

  "Calculator.futureCapital" should {
    "calculate future value of savings in n-months" in {
      val actual = Calculator.futureCapital(
        FixedReturns(0.04),
        nbOfMonths = 25 * 12,
        params.netIncome,
        params.currentExpenses,
        params.initialCapital)

      val expected = 541267.1990
      actual should ===(expected)
    }
  }

  "Calculator.simulatePlan" should {
    "calculate capital at retirement and at death" in {
      val (capitalAtRetirement, capitalAfterDeath) = Calculator.simulatePlan(
        FixedReturns(0.04),
        params,
        nbOfMonthsSaving = 25 * 12)

      capitalAtRetirement should ===(541267.1990)
      capitalAfterDeath should ===(309867.5316)
    }
  }

  "Calculator.nbOfMonthsSaving" should {
    "calculate how long I need to save before retiring" in {
      val actual = Calculator.nbOfMonthsSaving(
        FixedReturns(0.04),
        params)
      
      val expected = 23 * 12 + 1
      actual should ===(Some(expected))
    }
  }
}
