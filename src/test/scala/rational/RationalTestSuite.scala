import org.scalatest.funsuite.AnyFunSuite

import rational.Rational

class RationalTestSuite extends AnyFunSuite {
  test("constructor should setup member field 'numerator'") {
    val rational = new Rational(2, 3)
    assert(rational.numer == 2)
  }

  test("constructor should setup member field 'denominator'") {
    val rational = new Rational(2, 3)
    assert(rational.denom == 3)
  }
}
