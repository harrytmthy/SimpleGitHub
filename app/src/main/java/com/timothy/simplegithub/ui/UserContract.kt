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

import androidx.lifecycle.LiveData
import com.timothy.simplegithub.ui.model.UserModel
import io.reactivex.rxjava3.core.Observable

interface UserContract {

    sealed class State {
        object Empty : State()
        data class SearchSuccess(val data: List<UserModel>) : State()
        data class NextPage(val data: List<UserModel>) : State()
        data class Error(val message: String = "") : State()
    }

    interface Presenter {
        fun getState(): LiveData<State>
        fun observeTextChanges(textChangesObservable: Observable<String>)
        fun loadNextPage()
    }

    interface View {
        fun render(state: State)
    }
}