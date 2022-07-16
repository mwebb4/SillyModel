import org.scalatest.funsuite._
import data.DataGetter
import javax.xml.crypto.Data

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

  test("Load Data") {

    val f = fixture
    val resp = DataGetter.getData(f.localUrl + "users")
    val df = DataGetter.parseResponse(resp)

  }
}
