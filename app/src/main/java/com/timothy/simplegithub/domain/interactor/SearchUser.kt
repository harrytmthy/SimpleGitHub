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

import com.timothy.simplegithub.domain.FlowUseCase
import com.timothy.simplegithub.domain.common.Result
import com.timothy.simplegithub.domain.di.IoDispatcher
import com.timothy.simplegithub.domain.model.UserSearch
import com.timothy.simplegithub.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUser @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: UserRepository
) : FlowUseCase<SearchUser.Params, UserSearch>(dispatcher) {

    override fun execute(params: Params) = repository.searchUser(params.query, params.page)
        .map { Result.Success(it) }

    data class Params(val query: String, val page: Int)
}