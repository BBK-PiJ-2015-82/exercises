/**
  * Created by dannymadell on 28/01/2017.
  */
 object CaseEx {


  def countTens(alist: List[Int]): Int = alist match {
    case 10 :: tail => 1 + countTens(tail)
    case n :: tail => countTens(tail)
    case nil => 0
  }

  def testMatch(stringg: String) = stringg match {
    case "testString" => print(stringg)
    case "" => print("fuckalol")
    case other



  }

  def main(args: Array[String]): Unit = {
  val aList = 10 :: (2 :: Nil)
  println(countTens(aList))
    testMatch("testStghring")
    testMatch("")

  }


}
class CaseEx {

}
