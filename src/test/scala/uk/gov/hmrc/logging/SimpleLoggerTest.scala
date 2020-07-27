/*
 * Copyright 2020 HM Revenue & Customs
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

import org.scalatest.FunSuite
import org.slf4j.{Logger, Marker}

class SimpleLoggerTest extends FunSuite {
  val exception = new Exception("e")

  test("info with args") {
    val stub = new Slf4jStub()
    val log = new LoggerFacade(stub)
    log.info("abc", "x", "y", "z")

    assert(stub.infoMsg === "abc")
    assert(stub.infoArgs === Seq("x", "y", "z"))
    assert(stub.infoTh === stub.unset)

    assert(stub.warnMsg === "")
    assert(stub.warnArgs === Seq())
    assert(stub.warnTh === stub.unset)

    assert(stub.errorMsg === "")
    assert(stub.errorArgs === Seq())
    assert(stub.errorTh === stub.unset)

    assert(stub.debugMsg === "")
    assert(stub.debugArgs === Seq())
    assert(stub.debugTh === stub.unset)
  }

  test("warn with args") {
    val stub = new Slf4jStub()
    val log = new LoggerFacade(stub)
    log.warn("abc", "x", "y", "z")

    assert(stub.infoMsg === "")
    assert(stub.infoArgs === Seq())
    assert(stub.infoTh === stub.unset)

    assert(stub.warnMsg === "abc")
    assert(stub.warnArgs === Seq("x", "y", "z"))
    assert(stub.warnTh === stub.unset)

    assert(stub.errorMsg === "")
    assert(stub.errorArgs === Seq())
    assert(stub.errorTh === stub.unset)

    assert(stub.debugMsg === "")
    assert(stub.debugArgs === Seq())
    assert(stub.debugTh === stub.unset)
  }

  test("error with args") {
    val stub = new Slf4jStub()
    val log = new LoggerFacade(stub)
    log.error("abc", "x", "y", "z")

    assert(stub.infoMsg === "")
    assert(stub.infoArgs === Seq())
    assert(stub.infoTh === stub.unset)

    assert(stub.warnMsg === "")
    assert(stub.warnArgs === Seq())
    assert(stub.warnTh === stub.unset)

    assert(stub.errorMsg === "abc")
    assert(stub.errorArgs === Seq("x", "y", "z"))
    assert(stub.errorTh === stub.unset)
  
    assert(stub.debugMsg === "")
    assert(stub.debugArgs === Seq())
    assert(stub.debugTh === stub.unset)
  }

  test("debug with args") {
    val stub = new Slf4jStub()
    val log = new LoggerFacade(stub)
    log.debug("abc", "x", "y", "z")

    assert(stub.infoMsg === "")
    assert(stub.infoArgs === Seq())
    assert(stub.infoTh === stub.unset)

    assert(stub.warnMsg === "")
    assert(stub.warnArgs === Seq())
    assert(stub.warnTh === stub.unset)

    assert(stub.debugMsg === "abc")
    assert(stub.debugArgs === Seq("x", "y", "z"))
    assert(stub.debugTh === stub.unset)
  }

  test("info with exception") {
    val stub = new Slf4jStub()
    val log = new LoggerFacade(stub)
    log.info("abc", exception)

    assert(stub.infoMsg === "abc")
    assert(stub.infoArgs === Seq())
    assert(stub.infoTh === exception)

    assert(stub.warnMsg === "")
    assert(stub.warnArgs === Seq())
    assert(stub.warnTh === stub.unset)

    assert(stub.errorMsg === "")
    assert(stub.errorArgs === Seq())
    assert(stub.errorTh === stub.unset)
  
    assert(stub.debugMsg === "")
    assert(stub.debugArgs === Seq())
    assert(stub.debugTh === stub.unset)
  }

  test("warn with exception") {
    val stub = new Slf4jStub()
    val log = new LoggerFacade(stub)
    log.warn("abc", exception)

    assert(stub.infoMsg === "")
    assert(stub.infoArgs === Seq())
    assert(stub.infoTh === stub.unset)

    assert(stub.warnMsg === "abc")
    assert(stub.warnArgs === Seq())
    assert(stub.warnTh === exception)

    assert(stub.errorMsg === "")
    assert(stub.errorArgs === Seq())
    assert(stub.errorTh === stub.unset)

    assert(stub.debugMsg === "")
    assert(stub.debugArgs === Seq())
    assert(stub.debugTh === stub.unset)
  }

  test("error with exception") {
    val stub = new Slf4jStub()
    val log = new LoggerFacade(stub)
    log.error("abc", exception)

    assert(stub.infoMsg === "")
    assert(stub.infoArgs === Seq())
    assert(stub.infoTh === stub.unset)

    assert(stub.warnMsg === "")
    assert(stub.warnArgs === Seq())
    assert(stub.warnTh === stub.unset)

    assert(stub.errorMsg === "abc")
    assert(stub.errorArgs === Seq())
    assert(stub.errorTh === exception)
  
    assert(stub.debugMsg === "")
    assert(stub.debugArgs === Seq())
    assert(stub.debugTh === stub.unset)
  }

  test("debug with exception") {
    val stub = new Slf4jStub()
    val log = new LoggerFacade(stub)
    log.debug("abc", exception)

    assert(stub.infoMsg === "")
    assert(stub.infoArgs === Seq())
    assert(stub.infoTh === stub.unset)

    assert(stub.warnMsg === "")
    assert(stub.warnArgs === Seq())
    assert(stub.warnTh === stub.unset)

    assert(stub.errorMsg === "")
    assert(stub.errorArgs === Seq())
    assert(stub.errorTh === stub.unset)
  
    assert(stub.debugMsg === "abc")
    assert(stub.debugArgs === Seq())
    assert(stub.debugTh === exception)
    }
}

/** SLF4J Logger has a huge interface, difficult to test against. */
class Slf4jStub extends Logger {
  val unset = new Throwable("")

  var infoMsg: String = ""
  var infoArgs: Seq[AnyRef] = Seq()
  var infoTh: Throwable = unset

  var warnMsg: String = ""
  var warnArgs: Seq[AnyRef] = Seq()
  var warnTh: Throwable = unset

  var errorMsg: String = ""
  var errorArgs: Seq[AnyRef] = Seq()
  var errorTh: Throwable = unset

  var debugMsg: String = ""
  var debugArgs: Seq[AnyRef] = Seq()
  var debugTh: Throwable = unset

  override def warn(msg: String): Unit = ???

  override def warn(format: String, arg: scala.Any): Unit = ???

  override def warn(format: String, arguments: AnyRef*) {
    warnMsg = format
    warnArgs = arguments.toSeq
  }

  override def warn(format: String, arg1: scala.Any, arg2: scala.Any): Unit = ???

  override def warn(msg: String, t: Throwable) {
    warnMsg = msg
    warnTh = t
  }

  override def warn(marker: Marker, msg: String): Unit = ???

  override def warn(marker: Marker, format: String, arg: scala.Any): Unit = ???

  override def warn(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = ???

  override def warn(marker: Marker, format: String, arguments: AnyRef*): Unit = ???

  override def warn(marker: Marker, msg: String, t: Throwable): Unit = ???

  override def isErrorEnabled: Boolean = ???

  override def isErrorEnabled(marker: Marker): Boolean = ???

  override def getName: String = ???

  override def isInfoEnabled: Boolean = ???

  override def isInfoEnabled(marker: Marker): Boolean = ???

  override def isDebugEnabled: Boolean = ???

  override def isDebugEnabled(marker: Marker): Boolean = ???

  override def isTraceEnabled: Boolean = ???

  override def isTraceEnabled(marker: Marker): Boolean = ???

  override def error(msg: String): Unit = ???

  override def error(format: String, arg: scala.Any): Unit = ???

  override def error(format: String, arg1: scala.Any, arg2: scala.Any): Unit = ???

  override def error(format: String, arguments: AnyRef*) {
    errorMsg = format
    errorArgs = arguments.toSeq
  }

  override def error(msg: String, t: Throwable) {
    errorMsg = msg
    errorTh = t
  }

  override def error(marker: Marker, msg: String): Unit = ???

  override def error(marker: Marker, format: String, arg: scala.Any): Unit = ???

  override def error(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = ???

  override def error(marker: Marker, format: String, arguments: AnyRef*): Unit = ???

  override def error(marker: Marker, msg: String, t: Throwable): Unit = ???

  override def debug(msg: String): Unit = ???

  override def debug(format: String, arg: scala.Any): Unit = ???

  override def debug(format: String, arg1: scala.Any, arg2: scala.Any): Unit = ???

  override def debug(format: String, arguments: AnyRef*): Unit =  {
    debugMsg = format
    debugArgs = arguments.toSeq
  }

  override def debug(msg: String, t: Throwable): Unit = {
    debugMsg = msg
    debugTh = t
  }

  override def debug(marker: Marker, msg: String): Unit = ???

  override def debug(marker: Marker, format: String, arg: scala.Any): Unit = ???

  override def debug(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = ???

  override def debug(marker: Marker, format: String, arguments: AnyRef*): Unit = ???

  override def debug(marker: Marker, msg: String, t: Throwable): Unit = ???

  override def isWarnEnabled: Boolean = ???

  override def isWarnEnabled(marker: Marker): Boolean = ???

  override def trace(msg: String): Unit = ???

  override def trace(format: String, arg: scala.Any): Unit = ???

  override def trace(format: String, arg1: scala.Any, arg2: scala.Any): Unit = ???

  override def trace(format: String, arguments: AnyRef*): Unit = ???
  
  override def trace(msg: String, t: Throwable): Unit = ???

  override def trace(marker: Marker, msg: String): Unit = ???

  override def trace(marker: Marker, format: String, arg: scala.Any): Unit = ???

  override def trace(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = ???

  override def trace(marker: Marker, format: String, argArray: AnyRef*): Unit = ???

  override def trace(marker: Marker, msg: String, t: Throwable): Unit = ???

  override def info(msg: String): Unit = ???

  override def info(format: String, arg: scala.Any): Unit = ???

  override def info(format: String, arg1: scala.Any, arg2: scala.Any): Unit = ???

  override def info(format: String, arguments: AnyRef*) {
    infoMsg = format
    infoArgs = arguments.toSeq
  }

  override def info(msg: String, t: Throwable) {
    infoMsg = msg
    infoTh = t
  }

  override def info(marker: Marker, msg: String): Unit = ???

  override def info(marker: Marker, format: String, arg: scala.Any): Unit = ???

  override def info(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = ???

  override def info(marker: Marker, format: String, arguments: AnyRef*): Unit = ???

  override def info(marker: Marker, msg: String, t: Throwable): Unit = ???
}
