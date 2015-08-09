package quack

object Helpers {

  case class Lens[A, B](get: A => B, set: (A, B) => A) extends ((A) => B) with Immutable {
    def apply(whole: A): B = get(whole)

    // apply the (A => B) to some input
    def run(a: A): A = set(a, get(a))

    def updated(whole: A, part: B): A = set(whole, part)

    // like on immutable maps
    def mod(f: B => B) = new Lens[A, B](a => f(get(a)), { case (a, b) => set(a, b) })

    // Lens[A, B] + (B => B) = Lens[A, B]
    def map(f: B => B) = new Lens[A, B](
      a => f(get(a)),
      (a, b) => set(a, b)
    )


    // Composition with (B => B) and (A => Lens[A, B])
    // Lens[A, B] + (A => Lens[A, C]) = Lens[A, C]
    def flatMap[C](that: B => Lens[A, C]) = new Lens[A, C](
      a => that(get(a)).get(set(a, get(a))),
      (a, c) => that(get(a)).set(a, c)
    )

    def and(that: Lens[A, B]) = new Lens[A, B](
      a => that.get(set(a, get(a))),
      (a, b) => set(a, b)
    )
  }

  implicit class ListLens[A, B](l: Lens[A, List[B]]) {
    def +=(b: B) = l.map(xs => b :: xs)
  }

  implicit class SetLens[A, B](l: Lens[A, Set[B]]) {
    def +=(b: B) = l.map(xs => xs + b)
  }


}

