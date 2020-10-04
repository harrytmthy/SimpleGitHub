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

package com.timothy.simplegithub.data.source.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSearchRequest(
    @SerialName("q") val query: String,
    @SerialName("page") val pageNumber: Int = INITIAL_PAGE,
    @SerialName("per_page") val pageSize: Int = DEFAULT_PAGE_SIZE
) {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
        private const val INITIAL_PAGE = 1
    }
}