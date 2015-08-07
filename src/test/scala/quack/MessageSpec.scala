package quack

import org.scalatest.{BeforeAndAfter, FunSpec}

import scala.collection.mutable.ListBuffer

class MessageSpec extends FunSpec with BeforeAndAfter {
  var greeting: Message = _
  var chat: Message = _
  var farewell: Message = _
  var unordered: Seq[Message] = _
  var ordered: Seq[Message] = _
  before(
    greeting = new Message("Hello World."),
    chat = new Message("Weather."),
    farewell = new Message("Goodbye World."),
    unordered = ListBuffer(greeting, farewell, chat),
    ordered = ListBuffer(farewell, chat, greeting)
  )
  describe("A Message") {
    it("should be sortable by time created") {
      assert(unordered.sorted == ordered)
    }
  }
}
