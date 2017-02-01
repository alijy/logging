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

import java.util.concurrent.ConcurrentLinkedQueue

import org.slf4j.helpers.MessageFormatter

import scala.collection.JavaConverters._

/**
  * Captures log events for unit testing. Thread-safe.
  */
class StubLogger(echo: Boolean = false) extends SimpleLogger {
  private val _infos = new ConcurrentLinkedQueue[LogEntry]()
  private val _warns = new ConcurrentLinkedQueue[LogEntry]()
  private val _errors = new ConcurrentLinkedQueue[LogEntry]()
  private val _debugs = new ConcurrentLinkedQueue[LogEntry]()

  def clear() {
    synchronized {
      _infos.clear()
      _warns.clear()
      _errors.clear()
      _debugs.clear()
    }
  }

  def infos = _infos.asScala.toList

  def warns = _warns.asScala.toList

  def errors = _errors.asScala.toList

  def debugs = _debugs.asScala.toList

  def all = debugs ++ errors ++ warns ++ infos

  def size = all.size

  def isEmpty = all.isEmpty

  def info(format: String, arguments: AnyRef*) {
    val entry = LogEntry("INFO", format, arguments.toSeq, None)
    _infos.add(entry)
    if (echo) entry.dump()
  }

  def info(msg: String, t: Throwable) {
    val entry = LogEntry("INFO", msg, Seq(), Some(t))
    _infos.add(entry)
    if (echo) entry.dump()
  }

  def warn(format: String, arguments: AnyRef*) {
    val entry = LogEntry("WARN", format, arguments.toSeq, None)
    _warns.add(entry)
    if (echo) entry.dump()
  }

  def warn(msg: String, t: Throwable) {
    val entry = LogEntry("WARN", msg, Seq(), Some(t))
    _warns.add(entry)
    if (echo) entry.dump()
  }

  def error(format: String, arguments: AnyRef*) {
    val entry = LogEntry("ERROR", format, arguments.toSeq, None)
    _errors.add(entry)
    if (echo) entry.dump()
  }

  def error(msg: String, t: Throwable) {
    val entry = LogEntry("ERROR", msg, Seq(), Some(t))
    _errors.add(entry)
    if (echo) entry.dump()
  }

  def debug(format: String, arguments: AnyRef*) {
    val entry = LogEntry("DEBUG", format, arguments.toSeq, None)
    _debugs.add(entry)
    if (echo) entry.dump()
  }

  def debug(msg: String, t: Throwable) {
    val entry = LogEntry("DEBUG", msg, Seq(), Some(t))
    _debugs.add(entry)
    if (echo) entry.dump()
  }
}


case class LogEntry(prefix: String, message: String, args: Seq[AnyRef], throwable: Option[Throwable]) {
  override lazy val toString: String = prefix + MessageFormatter.arrayFormat(message, args.toArray).getMessage

  def dump() {
    println(toString)
    if (throwable.isDefined) {
      throwable.get match {
        case e: Exception => e.printStackTrace(System.out)
        case _ =>
      }
    }
  }
}
