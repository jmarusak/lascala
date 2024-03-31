case class Book(title: String, authors: String*)

val books: List[Book] =
List(
  Book(
    "Structure and Interpretation of Computer Programs",
    "Abelson, Harold", "Sussman, Gerald J."
  ),
  Book(
    "Principles of Compiler Design",
    "Aho, Alfred", "Ullman, Jeffrey"
  ),
  Book(
    "Programming in Modula-2",
    "Wirth, Niklaus"
  ),
  Book(
    "Elements of ML Programming",
    "Ullman, Jeffrey"
  ),
  Book(
    "The Java Language Specification", "Gosling, James",
    "Joy, Bill", "Steele, Guy", "Bracha, Gilad"
  )
)

for {
  b <- books
  a <- b.authors
  if a startsWith "Gosling"
} yield b.title
  
for (b <- books; if (b.title indexOf "Program") >= 0)
  yield b.title

val xtime =
for {
  b1 <- books
  b2 <- books
  if b1 != b2
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1

println(xtime)

def dedup[T](xs: List[T]): List[T] =
  if (xs.isEmpty) List()
  else xs.head :: dedup(xs.tail filter (x => x != xs.head))

println(dedup(xtime))
