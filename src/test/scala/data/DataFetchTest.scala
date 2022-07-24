import org.scalatest.funsuite._
import data.DataLoader
import utils.spark.SparkSessionFactory

class DataSuite extends AnyFunSuite {

  class Fixture {
    val spark = SparkSessionFactory.getSpark(local = true)
    val dataPath = getClass.getResource("/data.csv").getPath
    val featureCols = Array("x_0", "x_1", "x_2", "x_3", "x_4")
    val targetCol = "y"
  }

  def fixture = new Fixture

  test("Basic Spark DF") {

    val f = fixture
    val dg = new DataLoader(
      spark = f.spark,
      featureCols = f.featureCols,
      targetCol = f.targetCol
    )
    val df = dg.getData(f.dataPath)

    df.printSchema()

    assert(df.columns contains "features")
    assert(df.columns contains "label")
  }
}
