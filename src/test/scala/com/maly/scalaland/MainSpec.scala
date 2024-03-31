package com.maly.scalaland

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class MainSpec extends AnyWordSpec with Matchers {
  "A Person object" should {
    "sets name and age" in {
      val person = Person("peter", 63)
      person.name should be ("peter")
    }
  }

  "THe Person companion object" should {
    val (peter, john, ave, ivy) = (
      Person("peter", 45),
      Person("john", 40),
      Person("ave", 6),
      Person("ivy", 1)
    )

    "returns adult person list" in {
      val people = List(peter, john, ave, ivy)
      Person.filterAdult(people) should be (List(peter, john))
    }

    "returns empty person list" in {
      val people = List(ave, ivy)
      Person.filterAdult(people) should be (List.empty[Person])
    }
  }
}
