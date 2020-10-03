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

package com.timothy.simplegithub.data.source.network.response

import com.timothy.simplegithub.data.model.UserEntity
import com.timothy.simplegithub.data.model.UserSearchEntity
import com.timothy.simplegithub.data.util.DateTimeUtil
import kotlinx.serialization.SerialName

data class UserSearchResponse(
    @SerialName("total_count") val totalCount: Long,
    @SerialName("incomplete_results") val incomplete: Boolean,
    val items: List<UserResponse>
) {

    fun toUserEntity(query: String, pageNumber: Int) = UserSearchEntity(
        query = query,
        page = pageNumber,
        timestamp = DateTimeUtil.getCurrentMillis(),
        result = items.map {
            UserEntity(avatarUrl = it.avatarUrl, username = it.username)
        }
    )
}