package data
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.ml.feature.VectorAssembler
import com.typesafe.scalalogging.LazyLogging

class DataLoader(
    spark: SparkSession,
    featureCols: Array[String],
    targetCol: String
) extends LazyLogging {

  def getData(dataPath: String): DataFrame = {

    logger.info("Build DataFrame...")
    var df = spark.read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(dataPath)

    df = this.assembleFeatures(df)

    df.withColumnRenamed(this.targetCol, "label")
  }

  def train_test_split(
      data: DataFrame,
      split: Array[Double] = Array(0.9, 0.1)
  ): Array[DataFrame] = {

    data.randomSplit(split, seed = 12345)

  }

  private def convertDTypes(data: DataFrame): DataFrame = {
    return data
  }

  private def assembleFeatures(data: DataFrame): DataFrame = {

    val assembler = new VectorAssembler

    assembler.setInputCols(this.featureCols)
    assembler.setOutputCol("features")

    assembler.transform(data)

  }
}
