package utils.config

import org.rogach.scallop._

class Config(arguments: Seq[String]) extends ScallopConf(arguments) {
  val datapath = opt[String](required = true, descr = "Path to training data.")
  val features =
    opt[String](required = true, descr = "Comma-separated feature names.")
  val target = opt[String](required = true, descr = "Name of target columns.")
  verify()
}
