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

    private fun searchUser(query: String, page: Int = 1) = searchUser(
        params = SearchUser.Params(query, page),
        onSuccess = ::onSearchUserSuccess,
        onError = ::onSearchUserError
    ).also { currentQuery = query }

    private fun onSearchUserSuccess(userSearch: UserSearch) {
        if (currentPage == 1) {
            state.postValue(State.SearchSuccess(UserModel.from(userSearch)))
        } else {
            state.postValue(State.NextPage(UserModel.from(userSearch)))
        }
    }

    private fun onSearchUserError(e: Throwable) = state.postValue(State.Error(e.message.orEmpty()))

    override fun loadNextPage() = searchUser(currentQuery, ++currentPage)
}