package quack

import java.time.Instant

import org.scalatest.{BeforeAndAfter, FunSpec}
import quack.Helpers._

class UserSpec extends FunSpec with BeforeAndAfter {
  val time = Instant.now
  var adam: User = _
  var john: User = _
  var james: User = _
  var greeting: Message = _
  var chat: Message = _
  var farewell: Message = _
  var messages: List[Message] = _
  before(
    adam = User("Adam"),
    john = User("John"),
    james = User("James"),
    greeting = new Message("Hello World", time),
    chat = new Message("Weather", time.plusSeconds(5)),
    farewell = new Message("Goodbye World", time.plusSeconds(10)),
    messages = List[Message](farewell, chat, greeting)
  )
  describe("A User") {
    it("should be able to follow other users") {
      val followModLens =
        (User.followingLens += john) and
          (User.followingLens += james)
      adam = followModLens.run(adam)
      assert(adam.following == Set(john, james))
    }
//    it("should be able to view an aggregated list of all messages from the users they follow") {
//      val followModLens =
//        (User.followingLens += john) and
//          (User.followingLens += james)
//
//      val adamTModLens = User.timelineLens += greeting
//      val johnTModLens = User.timelineLens += chat
//      val jamesTModLens = User.timelineLens += farewell
//      adam = adamTModLens.run(adam)
//      john = johnTModLens.run(john)
//      james = jamesTModLens.run(james)
//      adam = followModLens.run(adam)
//      assert(adam.wall == messages)
//
//    }
    it("should be able to publish messages to a personal timeline") {
      val timelineModLens =
        (User.timelineLens += greeting) and
          (User.timelineLens += chat) and
          (User.timelineLens += farewell)

      adam = timelineModLens.run(adam)
      assert(adam.timeline == messages)
    }
  }
}
