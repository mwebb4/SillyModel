package data

object DataGetter {

  def getData(url: String): requests.Response = {

    val resp = requests.get(url)

    return resp

  }

  def main(url: String): String = {

    val resp = requests.get(url)

    return resp.text
  }

}
