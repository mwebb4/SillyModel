package main

import utils.config.Config
import com.typesafe.scalalogging.LazyLogging
import utils.spark.SparkSessionFactory
import data.DataGetter
import model.Model

object Main extends LazyLogging {

  def main(args: Array[String]): Unit = {

    logger.info("Parsing arguments...")
    val conf = new Config(args)

    logger.info("Configuring Spark...")
    val spark = SparkSessionFactory.getSpark()

    logger.info("Fetching data...")
    val dg = new DataGetter(spark)
    val df = dg.getData(conf.url.getOrElse("whoops"))

    logger.info("Training Model...")
    val model = new Model(spark)
    val Array(trainData, testData) = model.train_test_split(df)
    model.train(trainData)

  }
}
