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

import com.timothy.simplegithub.data.model.UserEntity
import com.timothy.simplegithub.data.model.UserSearchEntity
import com.timothy.simplegithub.data.source.FakeUserDataSource
import com.timothy.simplegithub.data.source.network.request.UserSearchRequest
import com.timothy.simplegithub.domain.model.UserSearch
import kotlinx.coroutines.flow.first
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual

/**
 * A TEMPLATE METHOD Pattern for [UserDataRepository] to simplify unit test
 * and remove code duplications on given/when/then part.
 */
abstract class UserDataRepositoryTestTemplate {

    private lateinit var userDataRepository: UserDataRepository

    private lateinit var request: UserSearchRequest

    protected lateinit var localResult: UserSearchEntity

    protected lateinit var networkResult: UserSearchEntity

    private lateinit var searchResult: UserSearch

    protected fun givenUserDataRepository(
        localHasResult: Boolean,
        networkHasResult: Boolean,
        expiredTimestamp: Boolean = false
    ) {
        request = UserSearchRequest("test", 1)
        localResult = getUserSearchEntity(localHasResult, expiredTimestamp)
        networkResult = getUserSearchEntity(networkHasResult)
        userDataRepository = UserDataRepository(
            localUserDataSource = FakeUserDataSource(localResult),
            networkUserDataSource = FakeUserDataSource(networkResult)
        )
    }

    protected suspend fun whenSearchUserIsCalled() = with (request) {
        searchResult = userDataRepository.searchUser(query, pageNumber).first()
    }

    protected fun thenSearchResultShouldBeEqualTo(expectedResult: UserSearch) =
        assertThat(searchResult, IsEqual(expectedResult))

    private fun getUserSearchEntity(
        hasResult: Boolean,
        expiredTimestamp: Boolean = false
    ) = when {
        expiredTimestamp -> getUserSearchEntityWithExpiredTimeStamp()
        hasResult -> getUserSearchEntityWithResult()
        else -> getUserSearchEntityWithoutResult()
    }

    private fun getUserSearchEntityWithExpiredTimeStamp() = with (request) {
        UserSearchEntity(
            query = query,
            page = pageNumber,
            timestamp = 0L,
            result = getExpiredUserEntities()
        )
    }

    private fun getUserSearchEntityWithResult() = with (request) {
        UserSearchEntity(
            query = query,
            page = pageNumber,
            result = getUserEntities()
        )
    }

    private fun getUserSearchEntityWithoutResult() = with (request) {
        UserSearchEntity(
            query = query,
            page = pageNumber
        )
    }

    private fun getUserEntities() = listOf(
        UserEntity("http://img.com/1.jpg", "pewds"),
        UserEntity("http://img.com/2.jpg", "toast"),
        UserEntity("http://img.com/3.jpg", "valkyrae")
    )

    private fun getExpiredUserEntities() = listOf(
        UserEntity("http://img.com/1.jpg", "pewds"),
        UserEntity("http://img.com/2.jpg", "toast")
    )
}