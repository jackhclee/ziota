import slick.jdbc.DatabaseUrlDataSource
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object DbApp {

  def main(argv: Array[String]) = {
    case class Coffee(name: String, price: Double)

    val src = new DatabaseUrlDataSource()
    src.setDriver("org.postgresql.Driver")
    src.url = "postgres://postgres:postgres@localhost/postgres"
    val db = Database.forDataSource(src, None)

    class CoffeeTable(tag: Tag) extends Table[(String, Double)](tag, "coffee") {
      def name = column[String]("name")
      def price = column[Double]("price")
      def * = (name, price)
    }

    // The `TableQuery` object gives us access to Slick's rich query API
    val coffees = TableQuery[CoffeeTable]

    val insertActions = DBIO.seq(
      // Inserting is done by appending to our query object
      // as if it were a regular Scala collection
      // SQL: insert into COFFEES (NAME, PRICE) values ('Latte', 2.50)
      coffees += ("Latte", 2.50),
      // Fetching data is also done using the query object
      // SQL: select NAME from COFFEES
      coffees += ("Amricano", 4.50)
    )

    // More complex queries can be chained together
    // SQL: select NAME, PRICE from COFFEES where PRICE < 10.0 order by NAME

    //val q = for (c <- coffees) yield c.name
    //val a = q.result
    val result = for {
      r1 <- db.run(insertActions)
      r2 <- db.run(coffees.filter(_.price < 2000.0).sortBy(_.name).result)
    } yield r2

    result.map(r => r.zipWithIndex.foreach(
      x => println(s"${x._2}: ${x._1._1}, ${x._1._2}"))
    )
    println("press any key to stop ...")
    val r = scala.io.StdIn.readLine()
  }
}
