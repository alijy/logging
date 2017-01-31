/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.logging

import org.slf4j.Logger

/** Logging interface to be injected into classes that use logging. */
trait SimpleLogger {
  def info(format: String, arguments: AnyRef*)

  def info(msg: String, t: Throwable)

  def warn(format: String, arguments: AnyRef*)

  def warn(msg: String, t: Throwable)

  def error(format: String, arguments: AnyRef*)

  def error(msg: String, t: Throwable)

  def debug(format: String, arguments: AnyRef*)

  def debug(msg: String, t: Throwable)

  def trace(format: String, arguments: AnyRef*)

  def trace(msg: String, t: Throwable)
}


/** Main adapter for SLF4J. Wire this in via DI. */
class LoggerFacade(underlying: Logger) extends SimpleLogger {
  override def info(format: String, arguments: AnyRef*) {
    underlying.info(format, arguments: _*)
  }

  override def info(msg: String, t: Throwable) {
    underlying.info(msg, t)
  }

  override def warn(format: String, arguments: AnyRef*) {
    underlying.warn(format, arguments: _*)
  }

  override def warn(msg: String, t: Throwable) {
    underlying.warn(msg, t)
  }

  override def error(format: String, arguments: AnyRef*) {
    underlying.error(format, arguments: _*)
  }

  override def error(msg: String, t: Throwable) {
    underlying.error(msg, t)
  }

  override def debug(format: String, arguments: AnyRef*) {
    underlying.debug(format, arguments: _*)
  }

  override def debug(msg: String, t: Throwable) {
    underlying.debug(msg, t)
  }
  
  override def trace(format: String, arguments: AnyRef*) {
    underlying.trace(format, arguments: _*)
  }

  override def trace(msg: String, t: Throwable) {
    underlying.trace(msg, t)
  }
}
