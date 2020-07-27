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

import org.slf4j.LoggerFactory

/**
  * LoggingFacade interacts by side-effect and is intrinsically hard to auto-test,
  * but it is sufficient to demonstrate that it is working manually.
  */
object LoggingFacadeTest extends App {

  println("---------- No output expected here ----------")
  runSamples(BlackHole)

  println("\n---------- Normal output expected here ----------")
  runSamples(Stdout)

  println("\n---------- Slf4J output expected here ----------")
  runSamples(new LoggerFacade(LoggerFactory.getLogger("test")))

  println("\n---------- StubLogger output expected here ----------")
  runSamples(new StubLogger(true))


  def runSamples(lg: SimpleLogger) {
    lg.info("T1 {}", "a")
    lg.info("T1 {} {}", "a", "b")
    lg.info("T1 {} {} {}", "a", "b", "c")
    lg.info("T2", new Exception("foo1"))

    lg.warn("T3 {}", "a")
    lg.warn("T3 {} {}", "a", "b")
    lg.warn("T3 {} {} {}", "a", "b", "c")
    lg.warn("T4", new Exception("foo2"))

    lg.error("T5 {}", "a")
    lg.error("T5 {} {}", "a", "b")
    lg.error("T5 {} {} {}", "a", "b", "c")
    lg.error("T6", new Exception("foo3"))

    lg.debug("T7 {}", "a")
    lg.debug("T7 {} {}", "a", "b")
    lg.debug("T7 {} {} {}", "a", "b", "c")
    lg.debug("T8", new Exception("foo4"))
  }
}
