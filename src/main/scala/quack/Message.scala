package quack

import java.time.LocalTime


class Message(message: String) extends Ordered[Message] {
  var timestamp = LocalTime.now
  override def compare(that: Message): Int = -(this.timestamp compareTo that.timestamp)

}
