import zio._

trait AuthorService {
  def count(): Long
}

case class AuthorServiceImpl() extends AuthorService {
  override def count(): Long = 2046
}

object AuthorServiceImpl {
  def getCount() = {
    for {
      bs <- ZIO.service[AuthorService]
    } yield (bs.count())
  }
}