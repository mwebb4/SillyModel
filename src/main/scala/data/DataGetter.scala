package data
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.Dataset

object DataGetter {

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .getOrCreate()

  def getData(url: String): requests.Response = {

    val resp = requests.get(url)

    return resp

  }

  def parseResponse(resp: requests.Response): DataFrame = {

    val jsonRdd = spark.sparkContext.parallelize(resp.text :: Nil)

    val df = spark.read.json(jsonRdd)

    df

  }

}
