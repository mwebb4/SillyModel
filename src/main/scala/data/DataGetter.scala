package data
//import spark.implicits._

object DataGetter {

  def getData(url: String): requests.Response = {

    val resp = requests.get(url)

    return resp

  }

  def parseResponse(resp: requests.Response): Unit = {

    // val df = spark.read.json(Seq(resp.text).toDS)

  }

}
