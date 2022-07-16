import org.scalatest.funsuite.{AnyFunSuite}
import model.Model

class ModelSuite extends AnyFunSuite {
  class Fixture {
    val trainArgs = Array("foo", "bar")
    val scoreArgs = Array("lok", "tar")
  }

  def fixture = new Fixture

  test("Train test") {

    val f = fixture

    Model.train(f.trainArgs)

    assert(true)

  }

  test("Score test") {

    val f = fixture

    Model.score(f.trainArgs)

    assert(true)

  }
}
