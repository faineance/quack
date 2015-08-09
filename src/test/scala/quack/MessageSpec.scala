package quack

import java.time.Instant

import org.scalatest.{BeforeAndAfter, FunSpec}

class MessageSpec extends FunSpec with BeforeAndAfter {
  val time = Instant.now
  var greeting: Message = _
  var chat: Message = _
  var farewell: Message = _
  var unordered: List[Message] = _
  var ordered: List[Message] = _
  before(
    greeting = new Message("Hello World", time),
    chat = new Message("Weather", time.plusSeconds(5)),
    farewell = new Message("Goodbye World", time.plusSeconds(10)),
    unordered = List(chat, farewell, greeting),
    ordered = List(farewell, chat, greeting)
  )
  describe("A Message") {
    it("should be sortable by time created") {

      assert(unordered.sorted== ordered)
    }
  }
}
