package quack

import org.scalatest.{BeforeAndAfter, FunSpec}

import scala.collection.mutable.ListBuffer

class UserSpec extends FunSpec with BeforeAndAfter {
  var adam: User = _
  var john: User = _
  var james: User = _
  var greeting: Message = _
  var chat: Message = _
  var farewell: Message = _
  var messages: ListBuffer[Message] = _
  before(
    adam = new User("Adam"),
    john = new User("John"),
    james = new User("James"),
    greeting = new Message("Hello World"),
    chat = new Message("Weather."),
    farewell = new Message("Goodbye World."),
    messages = ListBuffer[Message](farewell, chat, greeting)
  )
  describe("A User") {
    it("should be able to follow and unfollow other users") {
      adam.following += john
      adam.following += james
      assert(adam.following == Set(john, james))
      adam.following -= john
      assert(adam.following == Set(james))
    }
    it("should be able to publish messages to a personal timeline") {
      adam.timeline += greeting
      adam.timeline += chat
      adam.timeline += farewell
      assert(adam.timeline.reverse == messages)
    }
  }
}
