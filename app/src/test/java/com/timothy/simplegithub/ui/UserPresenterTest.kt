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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.timothy.simplegithub.domain.interactor.SearchUser
import com.timothy.simplegithub.domain.repository.FakeUserRepository
import com.timothy.simplegithub.ui.UserContract.State
import com.timothy.simplegithub.ui.ext.doOnErrorAnswer
import com.timothy.simplegithub.ui.ext.doOnSuccessAnswer
import com.timothy.simplegithub.ui.model.UserModel
import com.timothy.simplegithub.ui.util.LiveDataTestObserver
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserPresenterTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var searchUser: SearchUser

    @InjectMocks
    private lateinit var presenter: UserPresenter

    private val expectedData = UserModel.from(FakeUserRepository.USER_SEARCH)

    private val testObserver = LiveDataTestObserver<State>()

    @Before
    fun setup() = presenter.getState().observeForever(testObserver)

    @Test
    fun `observeTextChanges should emit success state`() {
        givenOnSuccessAnswer()

        whenTextChangesIsObserved("pew")

        thenSuccessStateShouldBeEmitted()
    }

    @Test
    fun `observeTextChanges with exception should emit error state`() {
        givenOnErrorAnswer()

        whenTextChangesIsObserved("pew")

        thenErrorStateShouldBeEmitted()
    }

    @Test
    fun `loadNextPage should emit success state`() {
        givenOnSuccessAnswer()

        whenNextPageIsLoaded()

        thenSuccessStateShouldBeEmitted()
    }

    @Test
    fun `loadNextPage with exception should emit error state`() {
        givenOnErrorAnswer()

        whenNextPageIsLoaded()

        thenErrorStateShouldBeEmitted()
    }

    @Test
    fun `observeTextChanges with empty query should emit empty state`() {
        // Given setup

        whenTextChangesIsObserved()

        thenEmptyStateShouldBeEmitted()
    }

    private fun givenOnSuccessAnswer() = doOnSuccessAnswer(FakeUserRepository.USER_SEARCH)
        .whenever(searchUser).invoke(any(), any(), any(), any())

    private fun givenOnErrorAnswer() = doOnErrorAnswer(Exception())
        .whenever(searchUser).invoke(any(), any(), any(), any())

    private fun whenTextChangesIsObserved(query: String = "") =
        presenter.observeTextChanges(Observable.just(query))

    private fun whenNextPageIsLoaded() = presenter.loadNextPage()

    private fun thenEmptyStateShouldBeEmitted() = testObserver
        .assertThatValueAt(0, State.Empty)
        .assertThatValueAt(1, State.Empty)

    private fun thenSuccessStateShouldBeEmitted() = testObserver
        .assertThatValueAt(0, State.Empty)
        .assertThatValueAt(1, State.Success(expectedData))

    private fun thenErrorStateShouldBeEmitted() = testObserver
        .assertThatValueAt(0, State.Empty)
        .assertThatValueAt(1, State.Error())
}