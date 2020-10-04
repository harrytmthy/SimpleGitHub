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

package com.timothy.simplegithub.domain.repository

import com.timothy.simplegithub.domain.model.User
import com.timothy.simplegithub.domain.model.UserSearch
import kotlinx.coroutines.flow.flow

class FakeUserRepository : UserRepository {

    override fun searchUser(query: String, page: Int) = flow { emit(USER_SEARCH) }

    companion object {
        private val USERS = listOf(
            User("http://img.com/1.jpg", "pewds"),
            User("http://img.com/2.jpg", "toast"),
            User("http://img.com/3.jpg", "valkyrae")
        )

        val USER_SEARCH = UserSearch(1, USERS)
    }
}