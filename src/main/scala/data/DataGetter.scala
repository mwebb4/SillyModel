package data
import org.apache.spark.sql.{SparkSession, DataFrame}
import com.typesafe.scalalogging.LazyLogging

class DataGetter(spark: SparkSession) extends LazyLogging {

  def getData(url: String): DataFrame = {
    logger.info("Fetching data...")
    val resp = requests.get(url)
    logger.info("Build Spark RDD...")
    val jsonRdd = spark.sparkContext.parallelize(resp.text :: Nil)
    logger.info("Build DataFrame...")
    val df = spark.read.json(jsonRdd)

    return df

  }

}
