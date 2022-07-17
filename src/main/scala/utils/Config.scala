package utils.config

import org.rogach.scallop._

class Config(arguments: Seq[String]) extends ScallopConf(arguments) {
  val datapath = opt[String](required = false, descr = "Path to training data.")
  verify()
}
