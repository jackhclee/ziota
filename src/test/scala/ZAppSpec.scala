import zio._
import zio.test._
import zio.test.Assertion._

import java.io.IOException

import HelloWorld._

import zio.System

object HelloWorld {
  def sayHello: ZIO[Any, Exception, Unit] = {
    for {
      envVar <- System.env("appName")
      _      <- Console.printLine(s"Hello, World! ${envVar.get}")
    } yield ()
  }
}

object HelloWorldSpec extends ZIOSpecDefault {
  def spec = suite("HelloWorldSpec")(
    test("sayHello correctly displays output") {
      for {
        _      <- TestSystem.putEnv("appName","ZIOTA")
        _      <- sayHello
        output <- TestConsole.output
      } yield assertTrue(output == Vector("Hello, World! ZIOTA\n"))
    }
  )
}