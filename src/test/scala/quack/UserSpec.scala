package quack

import org.scalatest.{BeforeAndAfter, FunSpec}

class UserSpec extends FunSpec with BeforeAndAfter {
  var adam: User = _
  var john: User = _
  var james: User = _
  before(
    adam = new User("Adam"),
    john = new User("John"),
    james = new User("James")

  )
  describe("A User") {
    it("should be able to follow and unfollow other users") {
      adam.following += john
      adam.following += james
      assert(adam.following == Set(john, james))
      adam.following -= john
      assert(adam.following == Set(james))
    }
  }
}
