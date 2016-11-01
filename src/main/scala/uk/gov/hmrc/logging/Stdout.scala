/*
 * Copyright 2016 HM Revenue & Customs
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

import org.slf4j.helpers.MessageFormatter

/** Logger to stdout for command-line apps. */
object Stdout extends SimpleLogger {
  override def info(format: String, arguments: AnyRef*) {
    val tp = MessageFormatter.arrayFormat(format, arguments.toArray)
    println(tp.getMessage)
  }

  override def info(msg: String, t: Throwable) {
    println(msg)
    t.printStackTrace(System.out)
  }

  override def warn(format: String, arguments: AnyRef*) {
    val tp = MessageFormatter.arrayFormat(format, arguments.toArray)
    println("WARN: " + tp.getMessage)
  }

  override def warn(msg: String, t: Throwable) {
    println("WARN: " + msg)
    t.printStackTrace(System.out)
  }

  override def error(format: String, arguments: AnyRef*) {
    val tp = MessageFormatter.arrayFormat(format, arguments.toArray)
    println("ERROR: " + tp.getMessage)
  }

  override def error(msg: String, t: Throwable) {
    println("ERROR: " + msg)
    t.printStackTrace(System.out)
  }
}




/** Sinks all messages written to it. */
object BlackHole extends SimpleLogger {
  override def info(format: String, arguments: AnyRef*) {}

  override def info(msg: String, t: Throwable) {}

  override def warn(format: String, arguments: AnyRef*) {}

  override def warn(msg: String, t: Throwable) {}

  override def error(format: String, arguments: AnyRef*) {}

  override def error(msg: String, t: Throwable) {}
}
