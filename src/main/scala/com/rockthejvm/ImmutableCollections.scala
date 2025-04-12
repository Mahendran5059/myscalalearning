package com.rockthejvm

import scala.collection.immutable.{HashMap, ListMap, ListSet, Queue, TreeMap,HashSet}



object ImmutableCollections extends App{
  //-----------------------List---------------------------------
  // slower search, fast for head access
  val myList = List[Int](11,2)
  val myListR = List.range[Char]('a','z')//List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y)
  // for include 'z' use List.range[Char]('a', ('z' + 1).toChar)
  // or ('a' to 'z').toList
  val myListT = List.tabulate(6)(_*2) //List(0, 2, 4, 6, 8, 10)
  val myListZ = 3::6::9::(12::15::Nil) // List(3,6,9,12,115)
  val myListZ1 = 11::(22::(33::Nil)) // List(11,22,33)
  // prepend
  // append is costly than prepend due to linked list nature
  val myListT1 = -1 :: myListT// List(-1,0,2,4,6,8,10)

  //append
  val myListT2 = myListT1:+16

  val myListT3 = myList::myListT //List(List(1,2),0,2,4,6,8,10)
  //concat two list
  val myListT4:List[Int] = myList:::myListT //List(11, 2, 0, 2, 4, 6, 8, 10)

  val head:Int = myListT2.head // -1
  val tail:List[Int] = myListT2.tail // List(0,2,4,6,8,10,16)
  val take:List[Int] = myListT2.take(2) //List(-1,0)

  //last 2 element
  println(myListT2.takeRight(2))

  //get elem by index
  //not recomended for random access by index in List , due to its costly
  // for randam in immutable collection, use vector
  val inval:Int = myListT2(2)// 2
  val emptyStrList = List.empty[String] //List()
  val mul = List.tabulate( 4,5 )( _ * _ ) //List(List(0, 0, 0, 0, 0), List(0, 1, 2, 3, 4),List(0, 2, 4, 6, 8), List(0, 3, 6, 9, 12))

  myListT3.indices // Range 0 until 7
  myListT3.init // List(List(11, 2), 0, 2, 4, 6, 8), other than last element
  myListT3.last // lats elem

  // Other methods
  val allElemGT5 = myListT4.forall(_>5) //false, forall(x => x>5)
  val existLst = myListT4.exists(_>5)// true, if one elem > 5 will return 5
  val strOfList = myListT2.mkString(",")// 11,2,0,2,4,6,8,10, list.mkString 1120246810
  // foreach, filter, isEmoty, iterator, min,max, sum, length, contains, copyToarray,
  // distinct, drop, dropRight, takeRight, dropWhile, equals, indexOf, intersect,
  //map, lastIndexOf, indexOf, endsWith, startWith, sorted,
  //toArray, toSet, toMap, toBuffer, toString
  myListT1.appended()
  println(myListR.iterator)

  //----------------------------ListSet-----------------------------------------------
  // List will preserve duplicates,
  // ListSet will not preserve duplicates
  // slower for larger dataset
  // head,tail,Empty similar to List
  val myListSet:ListSet[Int] = ListSet[Int](2,9,87,9,9) //ListSet(2,9,87)
  val myListSet1:ListSet[Int] = myListSet + 5 //ListSet(2, 9, 87, 5), add element
  val myListSet2 =  myListSet1 - 9 //ListSet(2,87,5), remove element
  val myListSet3 = ListSet(90,85) ++ myListSet //ListSet(90, 85, 2, 9, 87), concate two list

  // Random access or by index will not support, it will return contains that element or not
  myListSet(0) //false,  0 doesn't have,
  myListSet(9) // true, 9 there

  // intersection of two sets
  val intersect = myListSet & myListSet3 //ListSet(2, 9, 87),
  val intersect1 = myListSet.intersect(myListSet3) //ListSet(2, 9, 87)
  val outersect = myListSet &~ myListSet3 // List(), no diff element found in myListSet or list1
  val outersect1 = myListSet3 &~ myListSet //ListSet(90, 85), found diff element in List1
  val sub = myListSet3 -- myListSet //ListSet(90, 85), removed all the element of List2 from list1
  sub.product // 7650, product of (ListSet(90, 85)) elements of set
  sub.splitAt(1) //(ListSet(90),ListSet(85)), split first n elemt one set and rest another set and will return two set
  sub.size //2 , size instead of length
  // other methods similar to List,
  // extra there willl be a find, diff, some of List methods will not be here

  println(sub)

  //-------------------------------------ListMap--------------------------------------------
  val myMap:Map[Char,Int] = Map('a'->1,'b'->29)
  val myMap1 = myMap+ ('c'->3) //Map(a -> 1, b -> 2, c -> 3)
  val myMap2 = myMap ++ Map('z'->26) //Map(a->1,b->2,z->26)
  val myMap4 = myMap1 - ('b')//Map(a -> 1, c -> 3)
  val myMap5 = myMap2 -- myMap4.keysIterator //Map(b -> 29, z -> 26)
  myMap5('z')//26

  //iterate Map
  myMap5.keys.foreach(k => {println(s"key ${k} : ${myMap5(k)}")})
  myMap5.contains('z') // true , can check key exist
  myMap5.min // (b,29), giving min by keys
  myMap4.values
  // other methods are similar to Set or ListSet
  // clear, clone ,etc...


  println(myMap5.min)

  //-------------------------------ListMap--------------------------------------------------------
  // slower for large data set due to maintaining order, hashMap will fast
  val myListMap = ListMap("abc" -> 567)
  //ideal to Map methods , but maintains the ordering
  println(ListMap.empty[String,Int]) //ListMap()

  //------------------------------------HashMap-------------------------------
  // similar to Map in methods and operations
  // Map maintain insertion order , HashMap doesn't maintain any order
  // HaspMap is very fast than map, List Map
  val myhashmap = HashMap(("a",1),("b",2))
  //another way
  val myhasMap1 = HashMap("a" -> 1, "b"-> 2)

  // ---------------------------------TreeMap---------------------------------------
  //similar to Map in methods and operations
  //TreeMap stores the elements in sorted ascending order by key default.
  val mytreeMap = TreeMap("z"-> "abc", "b" -> "zxy", "a" -> "mnb")
  //print(mytreeMap) //TreeMap(a -> mnb, b -> zxy, z -> abc)
  val mytreeMap2 = mytreeMap + ("c" -> "spg")
  println(mytreeMap2)//TreeMap(a -> mnb, b -> zxy, c -> spg, z -> abc)

  //change the ascending order to descending oder
  object DescendingOrder extends Ordering[String] {
    override  def compare(key1:String, key2:String):Int = key2.compareTo(key1)

  }
  val mytreeMapDesc = TreeMap(mytreeMap.toSeq:_*)(DescendingOrder)
//  println(mytreeMapDesc)//TreeMap(z -> abc, b -> zxy, a -> mnb)

  //if you have ascending ordered reeMap already, use below will be simple one
  val reverseOrdering:Ordering[String] = Ordering[String].reverse // we can ake it as implicit, but it understand easily and without affecting other trees in , used explicitly
  val mytreeMapDesc2 = TreeMap(mytreeMap.toSeq:_*)(reverseOrdering)
  println(mytreeMapDesc2)

  //-------------------Queue-------------------------------
  // will follow first in first out FIFO principle.
  val myQueue:Queue[String]= Queue("The Antman","Thunivu","GBU")//Queue(The Antman, Thunivu, GBU)
  val myEnqueue: Queue[String] = myQueue.enqueue("AKxx")//Queue(The Antman, Thunivu, GBU, AKxx)
  myEnqueue.head//"The Antman"
  myEnqueue.tail// Queue(Thunivu, GBU, AKxx)

  val myQueueAppend = myQueue :+ "billa" //Queue(The Antman, Thunivu, GBU, billa)
  val myQueuePrepend = "billa2" +: myQueue //Queue(billa2, The Antman, Thunivu, GBU)
  val myDequeue :(String,Queue[String]) = myQueue.dequeue
  myDequeue._1 //The Antman
  myDequeue._2 //Queue(Thunivu, GBU)
  println(s"myQueue.dequeue._1 :${ myQueue.dequeue._1}")

  // access element by index
  println(s" myQueue index 0 : ${myQueue(0)}")
  println(s" myQueue index 1 : ${myQueue(1)}")

  //concat two queue
  val concatQueue =  myEnqueue ++ myQueue //Queue(The Antman, Thunivu, GBU, AKxx, The Antman, Thunivu, GBU)
//  println(concatQueue)

  // create empty queue
  val emptyQueue = Queue.empty[String] //Queue()
//  println(emptyQueue)

  //--------------------------Seq--------------------------
  //Sequence is interface , will use List implementation
  val myseq =  Seq("elem1","elem2") //List(elem1, elem2)
//  println(myseq)
  // all others are similar to List or other sequence

  //--------------------------Set-------------------------
  //set will maintain unique elements , will not maintain any order
  val mySet = Set("1","2","1","3")
  println(mySet)
// all other method and operations are similar to ListSet

  //-------------HashSet----------- also same, it is a concrete implementation of Set
  //it will store the elements using hashcode helpful for fast lookup
  val myhashset = HashSet("1","2","9","9")

  //----------TreeSet------------- same as other set collection,it is implemented using tree to store data
  // unique element, maintain ascending order. change to descending order by passing custom ordering implementation of Ordering class
  // methods and operations are similar to other Set

  //----------------------Streams---------------------------------
  // depreciated from scala 2.13.0
  // stream is lazyily computed means, compute when that particular element accessed or needed
  // head is strictly computed when creation and tail is lazy one
  // suitable for large list or infinite data or infinite number generation

  val mystream:Stream[Int] = 1 #:: 2 #:: 3 #:: Stream.empty // alternative cons(1,cons(2,cons(3, Stream.empty)))
//  println(mystream) //Stream(1, <not computed>), because, head strictly computed so we can see head element

  // print all element
  mystream.take(3).print()//1, 2, 3
  println()
  mystream.takeRight(3) //1,2,3

  // infinte series
  def infiniteNumberStream(number:Int):Stream[Int] =Stream.cons(number,infiniteNumberStream(number+1))

  infiniteNumberStream(1).take(4).print()

//  or
  val myinfinitestream:Stream[Int] = Stream.from(1) // from(start, step)
//  myinfinitestream.take(20).print()

  //EmptyStream
  val myEmptyStream = Stream.empty[Int] //Stream()
//  println(myEmptyStream)

  // ---------------------------LazyList--------------------------
//  alteernative to Stream.
  // it is fully lazy, even head will compute when neededy
  // other functionality are similar to other collections such as list, stream

  val myLazyList = LazyList(1,2,3,4) //LazyList(<not computed>)
  println(s" lazy list ${myLazyList(3)}")

  //----------------------------VECTOR-----------------------
  // similar to List , can say alternative to list.
  // it is faster that the list because it implemented using trie( tree like structure) data structure.
  // it will stored using bitmapped vector trie with 32 chunks .

  val myvector = Vector[String]("1","3","6")
  //access
  myvector(2)
  val myvector1 = myvector.updated(1,"7")//Vector(1, 7, 6), returned new vector
  val myvector2 = "8" +: myvector
  val myvector3 = myvector2 :+ "99"

  // concate vector
  val myconcatvector = myvector ++ myvector3
  println(myconcatvector)

  // empty vector
  Vector[String]() //Vector()

}
