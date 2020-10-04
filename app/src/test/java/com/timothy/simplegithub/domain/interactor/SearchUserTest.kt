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

package com.timothy.simplegithub.domain.interactor

import com.timothy.simplegithub.domain.common.Result
import com.timothy.simplegithub.domain.repository.FakeUserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Test

class SearchUserTest {

    private lateinit var searchUser: SearchUser

    @Before
    fun setup() {
        searchUser = SearchUser(
            dispatcher = TestCoroutineDispatcher(),
            repository = FakeUserRepository()
        )
    }

    @Test
    fun `execute with given params should successfully return the search result`() = runBlockingTest {
        // Given
        val params = SearchUser.Params("test", 1)

        // When
        val result = searchUser.execute(params).first()

        // Then
        assertThat(result, IsInstanceOf(Result.Success::class.java))
    }
}