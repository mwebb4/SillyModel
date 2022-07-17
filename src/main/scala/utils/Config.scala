package utils.config

import org.rogach.scallop._

class Config(arguments: Seq[String]) extends ScallopConf(arguments) {
  val url = opt[String](required = true, descr = "URL to training data server.")
  val datapath = opt[String](required = false, descr = "Path to training data.")
  verify()
}
