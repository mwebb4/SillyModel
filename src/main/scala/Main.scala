package main

import utils.config.Config
import com.typesafe.scalalogging.LazyLogging
import utils.spark.SparkSessionFactory
import data.DataGetter
import model.Model

object Main extends LazyLogging {

  val featureCols = Array("x_0", "x_1", "x_2", "x_3", "x_4")
  val targetCol = "y"

  def main(args: Array[String]): Unit = {

    logger.info("Parsing arguments...")
    val conf = new Config(args)

    logger.info("Configuring Spark...")
    val spark = SparkSessionFactory.getSpark()

    logger.info("Fetching data...")
    val dg = new DataGetter(spark, featureCols, targetCol)
    val df = dg.getData(conf.datapath.getOrElse("whoops"))

    logger.info("Training Model...")
    val model = new Model(spark, featureCols, targetCol)
    val Array(trainData, testData) = model.train_test_split(df)
    model.train(trainData)

  }
}
