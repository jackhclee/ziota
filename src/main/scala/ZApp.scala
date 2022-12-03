import zio.ZIOAppDefault
import zio.Console._
import zio._
object ZApp extends ZIOAppDefault {
  def run = for {
    _ <- printLine("Hello World")
    line <- readLine("Enter your name: ")
    _ <- printLine(s"Hello $line")
  } yield ()
}
