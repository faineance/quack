package quack

import java.time.Instant

case class Message(message: String, timestamp: Instant)

object Message {
  implicit val messageOrdering = new Ordering[Message] {
    override def compare(x: Message, y: Message): Int = -(x.timestamp compareTo y.timestamp)
  }

  def apply(message: String): Message = {
    new Message(message, Instant.now)
  }
}
