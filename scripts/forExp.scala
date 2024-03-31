case class Person (
  name: String,
  isMale: Boolean,
  children: Person*
)

val gwen = Person("Gwen", false)
val doug = Person("Doug", true)
val tammy = Person("Tammy", false, gwen, doug)
val dave = Person("Dave", true, gwen, doug)

val persons = List(gwen, doug, tammy, dave)

var result = for (person <- persons; if !person.isMale; child <- person.children)
  yield (person.name, child.name)

val result2 = persons.filter(p => !p.isMale).flatMap(p => p.children.map(c => (p.name, c.name)))

println(result)
println(result2)
