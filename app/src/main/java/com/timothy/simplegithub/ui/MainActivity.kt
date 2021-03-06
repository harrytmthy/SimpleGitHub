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

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.timothy.simplegithub.databinding.ActivityMainBinding
import com.timothy.simplegithub.ui.UserContract.State
import com.timothy.simplegithub.ui.adapter.UserAdapter
import com.timothy.simplegithub.ui.ext.addOnNextPageListener
import com.timothy.simplegithub.ui.ext.viewBinding
import com.timothy.simplegithub.ui.model.UserModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserContract.View {

    @Inject
    lateinit var presenter: UserContract.Presenter

    private lateinit var adapter: UserAdapter

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupAdapter()
        setupEndlessScroll()
        observeState()
        observeTextChanges()
    }

    private fun setupAdapter() {
        adapter = UserAdapter().also { binding.userRecyclerView.adapter = it }
    }

    private fun setupEndlessScroll() = binding.userRecyclerView.addOnNextPageListener {
        presenter.getState().value?.let {
            if (!isPagingDataEmpty(it)) {
                presenter.loadNextPage()
            }
        }
    }

    private fun isPagingDataEmpty(state: State) = state is State.NextPage && state.data.isEmpty()

    private fun observeState() = presenter.getState().observe(this, Observer(::render))

    private fun observeTextChanges() = presenter.observeTextChanges(
        binding.searchView.getTextChangesObservable()
    )

    override fun render(state: State) = when (state) {
        is State.Empty -> renderEmptyState()
        is State.SearchSuccess -> renderSuccessState(state.data)
        is State.NextPage -> renderNextPageState(state.data)
        is State.Error -> renderErrorState(state.message)
    }

    private fun renderEmptyState() = with (binding) {
        userRecyclerView.visibility = View.GONE
        noResultView.root.visibility = View.GONE
        adapter.removeAllUsers()
    }

    private fun renderSuccessState(users: List<UserModel>) = with (binding) {
        if (users.isNotEmpty()) {
            userRecyclerView.visibility = View.VISIBLE
            noResultView.root.visibility = View.GONE
        } else {
            noResultView.root.visibility = View.VISIBLE
            userRecyclerView.visibility = View.GONE
        }
        adapter.setUsers(users)
        binding.userRecyclerView.scrollToPosition(0)
    }

    private fun renderNextPageState(users: List<UserModel>) = adapter.addUsers(users)

    private fun renderErrorState(message: String) =
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT).show()
}