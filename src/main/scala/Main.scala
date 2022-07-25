package main

import utils.config.Config
import com.typesafe.scalalogging.LazyLogging
import utils.spark.SparkSessionFactory
import data.DataLoader
import model.Trainer
import org.apache.spark.ml.evaluation.RegressionEvaluator

object Main extends LazyLogging {

  def main(args: Array[String]): Unit = {

    logger.info("Parsing arguments...")
    val conf = new Config(args)
    val dataPath = conf.datapath.getOrElse("")
    val features = conf.features.getOrElse("").split(",")
    val target = conf.target.getOrElse("")
    val isLocal = conf.local.getOrElse(false)

    logger.info("Configuring Spark...")
    val spark = SparkSessionFactory.getSpark(local = isLocal)

    logger.info("Fetching data...")
    val dg =
      new DataLoader(spark, features, target)
    val df = dg.getData(dataPath)

    logger.info("Training Model...")
    val trainer =
      new Trainer(spark, features, target)

    var Array(trainData, testData) = dg.train_test_split(df)
    val (bestModel, summary) = trainer.train(trainData)

    logger.info("Model trained! Training data fit statistics:")
    logger.info(s"R2adj: ${summary.r2adj}")
    logger.info(s"RMSE: ${summary.rootMeanSquaredError}")
    logger.info(s"MAE: ${summary.meanAbsoluteError}")
    logger.info(s"Coefficients: ${bestModel.coefficients}")
    logger.info(s"Intercept: ${bestModel.intercept}")

    logger.info("Assessing Test set performance...")

    val testPreds = bestModel.transform(testData)

    var evaluator = new RegressionEvaluator()

    val rmseTest = evaluator.evaluate(testPreds)

    val r2Test = evaluator.setMetricName("r2").evaluate(testPreds)

    logger.info(s"Test set:\n R2adj:\t${r2Test}\n ExVar:\t${rmseTest}")

  }
}
