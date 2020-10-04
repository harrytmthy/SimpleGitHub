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

package com.timothy.simplegithub.data.source.network

import com.timothy.simplegithub.data.constants.UrlConstants.API_SEARCH_USER
import com.timothy.simplegithub.data.constants.UrlConstants.PARAM_PAGE_NUMBER
import com.timothy.simplegithub.data.constants.UrlConstants.PARAM_PAGE_SIZE
import com.timothy.simplegithub.data.constants.UrlConstants.PARAM_QUERY
import com.timothy.simplegithub.data.source.network.response.UserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserSearchFacade {

    @GET(API_SEARCH_USER)
    suspend fun searchUser(
        @Query(PARAM_QUERY) query: String,
        @Query(PARAM_PAGE_NUMBER) pageNumber: Int,
        @Query(PARAM_PAGE_SIZE) pageSize: Int
    ): UserSearchResponse
}