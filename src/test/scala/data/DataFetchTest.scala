import org.scalatest.funsuite._
import data.DataGetter

class DataSuite extends AnyFunSuite {

  class Fixture {
    val localUrl = "http://localhost:5000/"
  }

  def fixture = new Fixture

  test("Basic Connectivity") {

    val f = fixture
    val resp = DataGetter.getData(f.localUrl)

    println(resp.text)

    assert(resp.statusCode == 200)
  }
}
