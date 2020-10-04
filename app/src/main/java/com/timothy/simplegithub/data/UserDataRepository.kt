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

import com.timothy.simplegithub.data.di.Local
import com.timothy.simplegithub.data.di.Network
import com.timothy.simplegithub.data.source.UserDataSource
import com.timothy.simplegithub.data.source.network.request.UserSearchRequest
import com.timothy.simplegithub.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    @Local private val localUserDataSource: UserDataSource,
    @Network private val networkUserDataSource: UserDataSource
) : UserRepository {

    override fun searchUser(query: String, page: Int) = flow {
        val request = UserSearchRequest(query, page)
        localUserDataSource.searchUser(request).run {
            if (shouldLoadFromNetwork()) {
                emit(getUserSearchFromNetwork(request))
            } else {
                emit(this)
            }
        }
    }.map { it.toUserSearch() }

    private suspend fun getUserSearchFromNetwork(request: UserSearchRequest) =
        networkUserDataSource.searchUser(request).also {
            localUserDataSource.cacheUserSearch(it)
        }
}