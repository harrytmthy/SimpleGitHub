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

package com.timothy.simplegithub.ui.model

import com.timothy.simplegithub.domain.model.UserSearch

data class UserModel(val avatarUrl: String, val username: String) {

    companion object {
        fun from(userSearch: UserSearch) = userSearch.users.map {
            UserModel(it.avatarUrl, it.username)
        }
    }
}