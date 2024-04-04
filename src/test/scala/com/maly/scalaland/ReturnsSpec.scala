package com.maly.scalaland

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalactic.{Equality, TolerantNumerics, TypeCheckedTripleEquals}

class ReturnsSpec extends AnyWordSpec with Matchers with TypeCheckedTripleEquals {

  implicit val doubleEquality: Equality[Double] =
    TolerantNumerics.tolerantDoubleEquality(0.0001)
  
  "Returns.monthlyRate" should {
    "return fixed rate for any months" in {
      Returns.monthlyRate(FixedReturns(0.04), 0) should ===(0.04 / 12)
      Returns.monthlyRate(FixedReturns(0.04), 10) should ===(0.04 / 12)
    }
  }

  val returns = Vector(
    VariableReturn("2020.01", 0.1),
    VariableReturn("2020.02", 0.2),
    VariableReturn("2020.03", 0.3),
    VariableReturn("2020.04", 0.4),
    VariableReturn("2020.05", 0.5))

  "Returns.monthlyRate" should {
    "return variable rate fo n-month" in {
      Returns.monthlyRate(VariableReturns(returns), 0) should ===(0.1)
      Returns.monthlyRate(VariableReturns(returns), 1) should ===(0.2)
    }
  }

  "Returns.monthlyRate" should {
    "returns variable rate roll over for n-month > returns size" in {
      Returns.monthlyRate(VariableReturns(returns), 5) should ===(0.1)
      Returns.monthlyRate(VariableReturns(returns), 6) should ===(0.2)
    }
  }

  "VariableReturns.fromUntil" should {
    "filters returns between from and until " in {
      val variableReturns = VariableReturns(returns)
      variableReturns.fromUntil("2020.02", "2020.04").returns should ===(
        Vector(VariableReturn("2020.02", 0.2), VariableReturn("2020.03", 0.3)))
    }
  }
}
