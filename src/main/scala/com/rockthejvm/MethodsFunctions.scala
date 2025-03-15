package com.rockthejvm

object MethodsFunctions extends App{
  // function with only side effect
  def Hello(): Unit = {
    println("hello function")
  }
  // ----------Pure function----------
  //  purely doing specific defined function without doing other tasks such as side effects, etc...
  //  should return same output for same input at any cost
  def totalamount(quantity:Int) = {
    val price = 45.90
    quantity*price
  }



  Hello()
  println(s"total amount : ${totalamount(6)}")

}
