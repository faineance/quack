package quack

import quack.Helpers._

case class User(name: String, following: Set[User], timeline: List[Message]) {
  def wall: List[Message] = {
    val users = following + this
    users.flatMap(_.timeline).toList.sorted
  }
}

object User {

  val followingLens = Lens(
    get = (_: User).following,
    set = (user: User, following: Set[User]) => user.copy(following = following))
  val timelineLens = Lens(
    get = (_: User).timeline,
    set = (user: User, timeline: List[Message]) => user.copy(timeline = timeline))

  def apply(name: String): User = {
    new User(name, Set.empty[User], List.empty[Message])
  }

}


