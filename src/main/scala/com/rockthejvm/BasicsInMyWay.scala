package com.rockthejvm
import java.util
import scala.io.StdIn._
object BasicsInMyWay  {
 println("Basics Hello")

  def main(args: Array[String]): Unit = {
    // ----------------variable declaration---------------
    //scala code should not writtenas java, will not better performance than java.
    // val is immutable and equivalent to const , var is mutable
    val `max len` = "str max"
    val myString: String = "hey String"
    var _mine = "mine is oscillating"


    // Data types Short
    println(s"Hey, Main Methond ${`max len`} ")

    //Float
    val price: Float = 234.8776f
    //Long
    val thelong = 2783576493294L
    //Double
    val theDouble = 128973845647.9872398

    // ----------Type casting------------------
    println(theDouble.toString) // like wise toInt toFloat toLong ToChar toByte toBoolean etc..
    println(BigDecimal(theDouble).toString)
    // ---------------multi assign in single line --------------------
    val (f, s, t) = (1, 2, 3)
    println(f"f $f s $s t $t")
    //
    val donutsBought: Int = 5
    val bigNumberOfDonuts: Long = 100000000L
    val smallNumberOfDonuts: Short = 1
    val priceOfDonut: Double = 2.50
    val donutPrice: Float = 2.50f
    val donutStoreName: String = "learn about scala Donut Store"
    val donutByte: Byte = 0xa
    val donutFirstLetter: Char = 'D'
    val nothing: Unit = ()

    // -------------- if - else ---------------------------------------
    if (-1 > 0 && 1 < 100) println("if 1")
    else println("else -1")

    // ---------for -------------------------
    // for comprehension
    for (x <- 1 until 5) println(x)
    val TList = List(1, 2, 3, 4, 5, 234)
    println(" yield stmt", (for (x <- TList if 0 < x & x < 100) yield x).length)
    TList.filter(x => x == 1).foreach(println(_))

    // for with 2D array

    val TowDArray = new Array[String](2)
    val TwoDarrayA = Array(Array("amp", "map", "camp"), Array("cola", "mela", "kjnkjn"))

    for {
      x <- 0 until TwoDarrayA.length
      y <- 0 until TwoDarrayA(x).length

    } println(s"2D - array value : ${TwoDarrayA(x)(y)}")

    // ---------- range --------------------------------
    //using single quotes and not double quotes such that we end up with a character range
    val alphabetRangeFromAToZ = 'a' to 'z'
    val alphabetRangeFromAToZBy2 = 'a' to 'z' by 2

    val from0To10By2 = 0 to 10 by 2

    // Range to collection
    val listFrom1To5 = (1 to 5).toList
    //

    // -----------------Whilw loop -----------------
    var temp = 3
    while (temp > 0) {
      println("while", temp)
      temp -= 1
    }

    // ---------------do while loop-----------------
    do {
      println("i'm do while", temp)
      temp += 1
    } while (temp < 3)

    // -----------------pattern matching -------------------
    val tasteLevel3 = "nothing" match {
      case "Glazed Donut" | "Strawberry Donut" => "Very tasty"
      case "Plain Donut" => "Tasty"
      case _ => "Tasty"
    }

    // -------------------Option-----------------
    // it will retrun Some(value) or none
    // don't use option variable.get , use getOrElse
    // Option is a pseudo collection we  can treat as a collection
    val notNULL = Option("had value")
    val NULLvalue = Option(null)
    println(f"$notNULL%s and $NULLvalue%s")
    println(s"${notNULL.getOrElse("elsevalue")} and ${NULLvalue.getOrElse("hey else")}")

    notNULL match {
      case Some(name) => println(s"option using match $name")
      case None => println("Hye , this none")
    }
    // ------------------Enumeration------------------------------------

    object Life extends Enumeration {
      type Life = Value
      val value1: Value = Value("value1")
      val value2: Value = Value("value2")
      val value3: Value = Value("value3")
    }

    println(s"Enumeration ${Life.value1}  value id  : ${Life.value1.id}  values : ${Life.values}")
    Life.values.foreach {
      case v if v == Life.value2 || v == Life.value1 => println("enum ", v)
      case _ => None
    }
    // ---------------input from console --------------------------
    val userIn = readLine("ender userId ;")
    println(s"given userID ${userIn} \n Age : ")
    val age = readInt()
    println(s"given age ${age}")

    //


  }




}
