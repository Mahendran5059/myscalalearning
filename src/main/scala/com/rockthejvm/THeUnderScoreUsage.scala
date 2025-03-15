package com.rockthejvm

object THeUnderScoreUsage extends App {
  // Import all members of the scala.util package.
  import scala.util._

  // Import all members of scala.util except for Try.
  import scala.util.{Try => _, _}

  // Import all members of scala.util but rename Try to Attempt.
  import scala.util.{Try => Attempt, _}


  // ---use underscore as wildcard to match all types in type creators. For example, like List, Array, Seq, Option, and Vector
  def processLists(lists:List[_]) =lists.length
  println("list len :" + {processLists(List(1,2,"sudh",List(3,8)))});

  // -------------Ignored Parameter-------------------
  //use the underscore to ignore unused variables and types in the code.
  val practice = (1 to 5).map(_ => "practice")
  println(practice)

  // --------------------Partially-Applied Function---------------
  def multiply(x: Int, y: Int): Int = x * y
  val doubleWithFive = multiply(5, _: Int)
  val result = doubleWithFive(8)
  println("Partially-Applied Function multiply",result)
  println(( 1 to 5).map(_+0))


  //-----------------------Function Reassignment (Eta expansion)----------
  def add(a: Int, b: Int): Int = a + b

  val sum = add _ // reassign add to sum
  println(add(5, 7), sum(5, 7))

  // ----------------------Variable Argument Sequence--------------------------
  //convert sequence into variable arguments using 'seqName: _*' with type ascription

  def product(args: Int*): Int = {
    args.fold(1)(_ * _)
  }

  val factors = Seq(2, 3, 4)
  val productOfFactors = product(factors: _*)
  println("product Variable Argument Sequence",productOfFactors)

  // --------------------------Variable Initialization to its Default Value-----------------
  var count: Int = _
  println(count)


  //-------------------------Ignored Variable----------------------------
  val input = "apple,banana,carrot"
  val Array(first, _*) = input.split(",")
  println("Variable Initialization to its Default Value",first)

  //
}
