package basics

/**
  * Created by dannymadell on 26/01/2017.
  */
object Strip {




  sealed trait IList
  case class INil() extends IList
  case class ICons(head: Int, tail: IList) extends IList
  //The following function would add n to every number in a list:

  def add(n: Int, lst: IList): IList = alist match {
    case INil() => INil()
    case ICons(head, tail) => ICons(head + n, add(n, tail))
  }

  def main(args: Array[String]): Unit = {

  }
}
