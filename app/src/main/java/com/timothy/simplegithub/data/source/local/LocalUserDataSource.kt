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

package com.timothy.simplegithub.data.source.local

import com.timothy.simplegithub.data.db.AppDatabase
import com.timothy.simplegithub.data.model.UserSearchEntity
import com.timothy.simplegithub.data.source.UserDataSource
import com.timothy.simplegithub.data.source.network.request.UserSearchRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalUserDataSource @Inject constructor(
    private val database: AppDatabase
) : UserDataSource {

    override fun searchUser(request: UserSearchRequest) = flow {
        emit(queryCachedUserSearch(request.query, request.pageNumber))
    }

    private fun queryCachedUserSearch(query: String, page: Int) = database.userSearchDao()
        .getUserSearchResult(query, page)
        .firstOrNull()
        ?: UserSearchEntity(query, page)
}