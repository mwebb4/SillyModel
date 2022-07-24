package utils.spark

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession

object SparkSessionFactory extends LazyLogging {

  def getSpark(
      local: Boolean = false,
      appName: String = "LinRegSilly"
  ): SparkSession = {

    logger.info("Building SparkSession...")
    val spark = if (local) {
      SparkSession
        .builder()
        .appName(appName)
        .master("local[*]")
        .getOrCreate()

    } else {
      SparkSession
        .builder()
        .appName(appName)
        .getOrCreate()
    }
    spark
  }
}
