val capitals = Map("France" -> "Paris", "Czechia" -> "Prague")

val capital = capitals.get("France")

val y = capital match {
  case Some(s) => s
  case None => "?"
}

for ((country, capital) <- capitals) {
  println(s"$country: $capital")
}

val results = List(Some("apple"), None, Some("orange"))

for (Some(fruit) <- results)
  println(fruit)
