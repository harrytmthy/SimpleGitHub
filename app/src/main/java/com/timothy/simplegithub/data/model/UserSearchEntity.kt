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

package com.timothy.simplegithub.data.model

import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.TypeConverters
import com.timothy.simplegithub.data.converter.UserEntityConverter
import com.timothy.simplegithub.data.util.DateTimeUtil
import com.timothy.simplegithub.domain.model.UserSearch

@Entity
@Fts4
@TypeConverters(UserEntityConverter::class)
data class UserSearchEntity(
    val query: String,
    val page: Int,
    val timestamp: Long = DateTimeUtil.getCurrentMillis(),
    val result: List<UserEntity> = emptyList()
) {

    fun shouldLoadNewData() =
        DateTimeUtil.isPassedFiveMinutes(timestamp) || (result.isEmpty() && page == 1)

    fun toUserSearch() = UserSearch(
        page = page,
        users = result.map { it.toUser() }
    )
}