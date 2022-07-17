package utils.spark

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession

object SparkSessionFactory extends LazyLogging {

  def getSpark(): SparkSession = {

    logger.info("Building SparkSession...")
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    spark
  }
}
