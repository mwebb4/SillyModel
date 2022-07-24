import org.scalatest.funsuite.{AnyFunSuite}
import model.Model
import utils.spark.SparkSessionFactory
import data.DataGetter

class ModelSuite extends AnyFunSuite {
  class Fixture {
    val spark = SparkSessionFactory.getSpark(local = true)
    val dataPath = getClass.getResource("/data.csv").getPath
    val featureCols = Array("x_0", "x_1", "x_2", "x_3", "x_4")
    val targetCol = "y"
    val dg = new DataGetter(spark, featureCols, targetCol)
    var df = dg.getData(dataPath)
  }

  def fixture = new Fixture

  test("Train test") {

    val f = fixture
    val model = new Model(
      spark = f.spark,
      featureCols = f.featureCols,
      targetCol = f.targetCol
    )

    val (bestModel, summary) = model.train(f.df)

    assert(summary.r2adj > 0.5)

  }
}
