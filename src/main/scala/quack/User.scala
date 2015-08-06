package quack


import scala.collection.mutable

class User(name: String) {
  var following: mutable.Set[User] = new mutable.Set[User] {
    override def -=(elem: User): this.type = this -= elem

    override def +=(elem: User): this.type = this += elem

    override def contains(elem: User): Boolean = following.contains(elem)

    override def iterator: Iterator[User] = following.iterator
  }

}
