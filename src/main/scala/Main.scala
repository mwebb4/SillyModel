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
    val dataPath = conf.datapath.getOrElse("")
    val features = conf.features.getOrElse("").split(",")
    val target = conf.target.getOrElse("")

    logger.info("Configuring Spark...")
    val spark = SparkSessionFactory.getSpark()

    logger.info("Fetching data...")
    val dg =
      new DataGetter(spark, features, target)
    val df = dg.getData(conf.datapath.getOrElse(""))

    logger.info("Training Model...")
    val model =
      new Model(spark, conf.features.getOrElse("").split(","), target)
    val Array(trainData, testData) = model.train_test_split(df)
    model.train(trainData)

  }
}
