package com.rockthejvm

import java.time.LocalTime
import scala.language.implicitConversions
import scala.util.control.TailCalls._

object Currency extends Enumeration{
  type Currency = Value
  val USD: Value = Value("USD")
  val INR: Value = Value {
    "INR"
  }
}

object Designation extends Enumeration {
  type Designation = Value
  val SuperVisor: Value = Value("Supervisor")
  val SupportStaff: Value = Value("SupportStaff")
  val Manager: Value = Value("Manager")
  val Engineer: Value = Value("Engineer")
  val GeneralWorker: Value = Value("GeneralWorker")
}


object MethodsFunctions extends App{
  // function with only side effect
  // for singleline no need of code block {}
  def Hello(): Unit =
    println("hello function")
  Hello()
  // ----------Pure function----------
  //  purely doing specific defined function without doing other tasks such as side effects, etc...
  //  should return same output for same input at any cost
  // with default value
  def totalamount(quantity: Int, discountpercentage: Double = 1, couponcode: Option[String] = None): Double = {
    val price = 45.90

    // lastline is the return value
    couponcode match {
      case Some(coupon) => {
        println("coupon",coupon)
         quantity*price*discountpercentage*0.7}
      case _ =>{
        quantity * price * discountpercentage
      }
    }

  }
  println(s"total amount : ${totalamount(6)}")

// option as return type
  def optionAsreturntype(): Option[String] = {
    val bank = "SBI"
    Option(bank).filter(_.nonEmpty)
  }
  println(s"""total amount with coupon: ${totalamount(6,couponcode = Option("coupon value"))}
  Bank :${optionAsreturntype().getOrElse("no bank configured")}
  """)
  // --------------function with implicit parameter------------------
  // with currying
  implicit val defaultCurrency: Currency.Value = Currency.INR
  implicit val emptype: String = "FTE"
  def getTotalPay(designation:String,experience:Int)(noOfYears:Double = 1)(implicit employementType:String, currency:Currency.Currency): Double = {
    var totalPay:Float = 0
    val bonus:Float = 0.1F
    try{
      val desginenum = Designation.withName(designation)
      totalPay =desginenum match {
        case Designation.GeneralWorker => {(20000*12*experience*0.1*noOfYears).toFloat}
        case Designation.SupportStaff => {(25000*12*experience*0.1*noOfYears).toFloat}
        case Designation.SuperVisor =>{(30000*12*experience*0.1*noOfYears).toFloat}
        case Designation.Engineer => {(40000*12*experience*0.1*noOfYears).toFloat}
        case Designation.Manager => {(60000*12*experience*0.1*noOfYears).toFloat}
        case _ => 0.0F
      }
    }
    catch {
      case _ => {
        totalPay = 0.0F
      }
    }
    totalPay = employementType match {
      case "FTE" => totalPay/bonus
      case "Contract" => (totalPay/0.15).toFloat
      case _ => totalPay
    }

    currency match {
      case Currency.USD => totalPay/81
      case _ => totalPay
    }


  }


  println(s" totalPay ${getTotalPay("Engineer",2)()}")
  println(s" totalPay curr manual value${getTotalPay("Engineer",2)(2)}")

//-----------------------implicit function-------------------------------------
  //wrapper class same as normal calss
  class TrueOrFalse(value:String) {
    def isBooleanString:Boolean = value.toUpperCase() == "TRUE" || value.toUpperCase == "FALSE"

  }
  // object not need , can directly create in same scope instead of below way,
  // you have to import to access their members
  object Truefalse {
    implicit def isBoolStr(value:String): TrueOrFalse = new TrueOrFalse(value)
  }
import  Truefalse._
  print(s"implictin Fun isBooleanString output : ${"true".isBooleanString}")

  //--------------------variable agrument function / Higher order function as parameter ------------------------------------
  //put variable arg as last arg in multiparam function
  def variableagrmethod(detectTax:Double=>Double,arg:String*):Unit = {
    println("vatiable arg"+arg.mkString(","))
    println(s"call back ${detectTax(90000).toInt}")
  }

  val higherOrderParamfunction = (callbackfun:()=>Unit)  => callbackfun()

// you can pass function defined using def and val
  variableagrmethod((amt:Double)=>amt,"str1","str2","str3")

  // call by passing list|Array|seq
  variableagrmethod((amount:Double)=>amount*0.9,List("a","1","true"): _*)

  // call val function with call back
  higherOrderParamfunction(()=>println("higher order param as call back called"))

  //------------- you can define operator as function "self refer
  //------------- we n declare parameter type  of function as getemp(param1:Option[()=>Int])
  // accesss option type param function by param1.map(fun => fun())

  //------------------andThen-----------------------
  //(applyDiscount andThen applyTax)(totalCost)
  // first will call applyDiscount function and result of applydiscount fun will be passed to
  // applyTax function and call it as second

  //-----------------Compose-----------------
  /*Ordering using andThen: f(x) andThen g(x) = g(f(x))
Ordering using compose: f(x) compose g(x) = f(g(x))8/
Compose will execute second function after call first function by passing result of second function value
   */

  //--------------------tail recursion-------------------
  // using @annotation.tailrecur
  // prevent from call stack overflow and optimize as loop and fastern than loop
  // prventing call stack overflow by avoiding dependncy of previous call by carry forwarding result to currect call
  @annotation.tailrec
  def fibinocci(n:Int,first : Int =0,second:Int=1): Int = {
    if (n==0) first else fibinocci(n-1,second,first+second)
  }
  // non - tail recursion
  def fibinocciNontail(n:Int): Int = {
    if (n<=0) 0 else fibinocciNontail(n-1)+fibinocciNontail(n-2)
  }

  // can implement tail recursion withscala.util.control.TailCalls._ not covered
  // "Trampoline tail recursive" advance concept not covered


  //---------------------partially applied function------------
  // acheived by orElse
  val habitale:PartialFunction[String,Unit] = (planet:String)=>{
    planet.toUpperCase match {
      case "EARTH" => println(s"Planet ${planet} is habitable")
    }
  }

  val artificiallyHabitable:PartialFunction[String,Unit] = (planet:String)=> {
    planet.toUpperCase() match {
      case "MARS" => println(s"Planet ${planet} is aritificially habitable")
    }
  }

  val nonHabitable:PartialFunction[String,Unit] =(planet:String) => {
    planet.toUpperCase() match {
      case _ => println(s"Planet ${planet} non habitable")
    }
  }

  val liviableplanet = habitale orElse artificiallyHabitable orElse nonHabitable

  liviableplanet("earth")

  //-------------------------nested function--------------------
  // nested function only accessable only within parent function
  def checkDonutAvailabilityWithNestedFunction(donutName: String): Boolean = {
    // retrieve donut list that is currently in stock
    val listDonutsFromStock = List[String]("Vanilla Donut", "Strawberry Donut", "Plain Donut", "Glazed Donut")

    // validate the donutName parameter by some business logic
    val validate = (name: String) => {
      name.nonEmpty && name.nonEmpty
    }

    // first run validate and then check if we have a matching donut from our list
    val isDonutInStock = validate(donutName) && (listDonutsFromStock contains donutName)

    isDonutInStock
  }


}

