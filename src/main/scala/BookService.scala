import zio._

trait BookService {
  def count(): Long
}

case class BookServiceImpl() extends BookService {
  override def count(): Long = 1997
}

object BookServiceImpl {
  def getCount() = {
    for {
      bs <- ZIO.service[BookService]
    } yield (bs.count())
  }
}