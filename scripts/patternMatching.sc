val x = List(0, 1, 2)

val ismatch = x match {
  case List(_,_,2) => true
  case List(_,_) => false
}

print(ismatch)
