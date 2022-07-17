package model

import com.typesafe.scalalogging.LazyLogging

object Model extends LazyLogging {
  def train(): Unit = {
    logger.info("Train Model Called...")
  }

  def score(): Unit = {
    logger.info("Score Model Called...")
  }
}
