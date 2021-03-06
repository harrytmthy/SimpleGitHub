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

package com.timothy.simplegithub.data

import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class UserDataRepositoryTest : UserDataRepositoryTestTemplate() {

    @Test
    fun `searchUser without local result should return data from network`() = runBlockingTest {
        givenUserDataRepository(
            localHasResult = false,
            networkHasResult = true
        )

        whenSearchUserIsCalled()

        thenSearchResultShouldBeEqualTo(networkResult.toUserSearch())
    }

    @Test
    fun `searchUser without local result on expired timestamp should return data from network`() = runBlockingTest {
        givenUserDataRepository(
            localHasResult = false,
            networkHasResult = true,
            expiredTimestamp = true
        )

        whenSearchUserIsCalled()

        thenSearchResultShouldBeEqualTo(networkResult.toUserSearch())
    }

    @Test
    fun `searchUser with local result should return data from local`() = runBlockingTest {
        givenUserDataRepository(
            localHasResult = true,
            networkHasResult = false
        )

        whenSearchUserIsCalled()

        thenSearchResultShouldBeEqualTo(localResult.toUserSearch())
    }

    @Test
    fun `searchUser with local result and expired Timestamp should return data from network`() = runBlockingTest {
        givenUserDataRepository(
            localHasResult = true,
            networkHasResult = true,
            expiredTimestamp = true
        )

        whenSearchUserIsCalled()

        thenSearchResultShouldBeEqualTo(networkResult.toUserSearch())
    }
}