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

package com.timothy.simplegithub.ui

import androidx.lifecycle.MutableLiveData
import com.timothy.simplegithub.ui.common.RxLifecycleAwarePresenter
import com.timothy.simplegithub.domain.interactor.SearchUser
import com.timothy.simplegithub.domain.model.UserSearch
import com.timothy.simplegithub.ui.UserContract.State
import com.timothy.simplegithub.ui.model.UserModel
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@ActivityScoped
class UserPresenter @Inject constructor(
    private val searchUser: SearchUser
) : RxLifecycleAwarePresenter(), UserContract.Presenter {

    private val state = MutableLiveData<State>()

    private var currentPage = 1

    private var currentQuery = ""

    override fun getState() = state

    override fun observeTextChanges(textChangesObservable: Observable<String>) = launch {
        textChangesObservable.subscribe(::validateQuery)
    }

    private fun validateQuery(query: String) = query.takeIf { it.isNotEmpty() }
        ?.let(::searchUser)
        ?: state.postValue(State.Empty)

    private fun searchUser(query: String) = searchUser(
        params = SearchUser.Params(query, 1),
        onSuccess = ::onSearchUserSuccess,
        onError = ::onError
    ).also {
        currentQuery = query
        currentPage = 1
    }

    private fun searchUserNextPage(query: String, page: Int) = searchUser(
        params = SearchUser.Params(query, page),
        onSuccess = ::onNextPage,
        onError = ::onError
    )

    private fun onSearchUserSuccess(userSearch: UserSearch) =
        state.postValue(State.SearchSuccess(UserModel.from(userSearch)))

    private fun onNextPage(userSearch: UserSearch) =
        state.postValue(State.NextPage(UserModel.from(userSearch)))

    private fun onError(e: Throwable) = state.postValue(State.Error(e.message.orEmpty()))

    override fun loadNextPage() = searchUserNextPage(currentQuery, ++currentPage)
}