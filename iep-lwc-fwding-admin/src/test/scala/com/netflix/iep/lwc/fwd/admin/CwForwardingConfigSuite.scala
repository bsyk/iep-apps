/*
 * Copyright 2014-2019 Netflix, Inc.
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
package com.netflix.iep.lwc.fwd.admin

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.StrictLogging
import org.scalatest.FunSuite

class CwForwardingConfigSuite extends FunSuite with CwForwardingTestConfig with StrictLogging {

  val validations = new CwExprValidations(
    new ExprInterpreter(ConfigFactory.load())
  )

  test("Skip the given checks") {
    val config = makeConfig(checksToSkip = Seq("DefaultDimension"))
    assert(config.shouldSkip("DefaultDimension"))
  }

  test("Do checks that are not flagged to skip") {
    val config = makeConfig(checksToSkip = Seq("DefaultDimension"))
    assert(config.shouldSkip("SingleExpression") == false)
  }

}