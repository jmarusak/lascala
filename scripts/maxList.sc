def maxList[T](l: List[T])(ordering: Ordering[T]): T = {
  l match {
    case List(x) => x
    case x :: rest => {
      val maxRest = maxList(rest)(ordering)
      if (ordering.gt(x, maxRest)) x else maxRest
    }
  }
}

def maxList2(l: List[Int]): Int = {
  var max = Int.MinValue 
  l.foreach((x) => if (x > max) max = x)
  max
}

//val max = maxList(List(1,2,3,5,4,3))(Ordering[Int])
//val max = maxList(List("a", "c", "d"))(Ordering[String])
//println(max)

val people = List(("peter", 10), ("paul", 5))

val people_sorted = people.sortWith((x, y) => x._2 < y._2)
println(people_sorted)

val nums = List(1,4,3,2)
val nums_sorted = nums.sorted
println(nums_sorted)
