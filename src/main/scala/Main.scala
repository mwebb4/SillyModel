package main

import utils.config.Config
import com.typesafe.scalalogging.LazyLogging
import utils.spark.SparkSessionFactory
import data.DataLoader
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
      new DataLoader(spark, features, target)
    val df = dg.getData(dataPath)

    logger.info("Training Model...")
    val model =
      new Model(spark, features, target)

    val Array(trainData, testData) = dg.train_test_split(df)
    val (bestModel, summary) = model.train(trainData)

    println("Model trained! Training data fit statistics:")
    println(s"R2adj: ${summary.r2adj}")
    println(s"RMSE: ${summary.rootMeanSquaredError}")
    println(s"MAE: ${summary.meanAbsoluteError}")
    println(s"Coefficients: ${bestModel.coefficients}")
    println(s"Intercept: ${bestModel.intercept}")

  }
}
