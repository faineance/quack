package quack


import scala.collection.mutable


class User(name: String) {
  var following: mutable.Set[User] = new mutable.HashSet[User]
  var timeline: mutable.ListBuffer[Message] = new mutable.ListBuffer[Message]
}
