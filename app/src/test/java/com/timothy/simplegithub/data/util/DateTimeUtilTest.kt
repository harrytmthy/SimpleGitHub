/*
 * Copyright 2020 harrytmthy
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

package com.timothy.simplegithub.data.util

import org.junit.Assert.assertEquals
import org.junit.Test

class DateTimeUtilTest {

    @Test
    fun `isPassedFiveMinutes with past millis should return true`() {
        val pastMillis = 10000000L

        val result = DateTimeUtil.isPassedFiveMinutes(pastMillis)

        assertEquals(true, result)
    }

    @Test
    fun `isPassedFiveMinutes with current millis should return true`() {
        val currentMillis = System.currentTimeMillis()

        val result = DateTimeUtil.isPassedFiveMinutes(currentMillis)

        assertEquals(false, result)
    }
}