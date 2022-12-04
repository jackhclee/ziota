import zio.ZIOAppDefault
import zio.Console._
import zio._

import java.io.IOException
object ZApp extends ZIOAppDefault {
  val entry : ZIO[BookService & AuthorService, IOException, Unit] = for {
    _    <- printLine("Hello World")
    line <- readLine("Enter your name: ")
    _    <- printLine(s"Hello $line")
    bs   <- ZIO.service[BookService]
    _    <- printLine(s"BookService.count(): ${bs.count()}")
    as   <- ZIO.service[AuthorService]
    _    <- printLine(s"AuthorService.count(): ${as.count()}")
  } yield ()

  def run = {
    entry.provideEnvironment(
     ZEnvironment(BookServiceImpl()) ++ ZEnvironment(AuthorServiceImpl()))
  }

}
