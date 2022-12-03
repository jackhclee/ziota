import zio.ZIOAppDefault
import zio.Console._
import zio._
object ZApp extends ZIOAppDefault {
  def run = for {
    _ <- printLine("Hello World")
  } yield ()
}
