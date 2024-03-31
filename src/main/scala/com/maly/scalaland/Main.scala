package com.maly.scalaland

case class Person(name: String, age: Int)

object Person {
  def filterAdult(people: List[Person]): List[Person] = {
    people.filter(_.age >= 18)
  }
}

object Main extends App {
  val people = List(
    Person("peter", 12),
    Person("tom", 23),
    Person("ann", 18)
  )

  val adults = Person.filterAdult(people)
  println(adults)
}
