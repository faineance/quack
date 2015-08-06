package quack


object Quack extends App {

  sys addShutdownHook {
    println("Shutdown hook caught. Cleaning up.")
    println("Quitting.")
  }
}
