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

package com.timothy.simplegithub.domain

import com.timothy.simplegithub.domain.common.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

abstract class FlowUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) : CoroutineScope by CoroutineScope(coroutineDispatcher) {

    protected abstract fun execute(params: P): Flow<Result<R>>

    @JvmOverloads
    operator fun invoke(
        params: P,
        onLoading: () -> Unit = {},
        onSuccess: (data: R) -> Unit = {},
        onError: (e: Exception) -> Unit = {}
    ) {
        launch {
            execute(params)
                .catch { e -> emit(Result.Error(Exception(e))) }
                .flowOn(coroutineDispatcher)
                .collect { result ->
                    when (result) {
                        is Result.Loading -> onLoading()
                        is Result.Success -> onSuccess(result.data)
                        is Result.Error -> onError(result.exception).also {
                            Timber.e(result.exception)
                        }
                    }
                }
        }
    }
}