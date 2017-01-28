/**
  * Created by dannymadell on 27/01/2017.
  */

object Palindrome {
  def main(args: Array[String]): Unit = {


    def isPalindrome(s: String): Boolean = {
      //var stringBuilder = null
      var newString = (for (i <- s;
                            if (!":.?,;!-' ".contains(i)))
        yield i).toLowerCase()
      println(newString)
      println(newString.reverse)
      return newString.equals(newString.reverse)
    }

      println(isPalindrome("ABCdef ;'  FEDcba "))
  }
}
