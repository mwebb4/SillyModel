package model

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.ml.regression.{
  LinearRegressionModel,
  LinearRegression,
  LinearRegressionSummary
}
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.tuning.{ParamGridBuilder, CrossValidator}

class Trainer(
    spark: SparkSession,
    featureCols: Array[String],
    targetCol: String
) extends LazyLogging {

  def train(
      data: DataFrame
  ): (LinearRegressionModel, LinearRegressionSummary) = {

    logger.info("Train Model Called...")

    var lr = new LinearRegression()
      .setMaxIter(10)

    val paramGrid = new ParamGridBuilder()
      .addGrid(lr.regParam, Array(0.1, 0.01))
      .addGrid(lr.fitIntercept)
      .addGrid(lr.elasticNetParam, Array(0.0, 0.5, 1.0))
      .build()

    val cv = new CrossValidator()
      .setEstimator(lr)
      .setEvaluator(new RegressionEvaluator)
      .setEstimatorParamMaps(paramGrid)
      .setNumFolds(2) // Use 3+ in practice
      .setParallelism(2) // Evaluate up to 2 parameter settings in parallel

    // Run train validation split, and choose the best set of parameters.
    val cv_res = cv.fit(data)

    logger.info("Model successfully trained!")

    // Get Best Model and Training Metricss
    val bestModel = cv_res.bestModel.asInstanceOf[LinearRegressionModel]
    val summary = bestModel.summary.asInstanceOf[LinearRegressionSummary]

    return (bestModel, summary)
  }

}
