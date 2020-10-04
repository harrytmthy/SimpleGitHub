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
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.timothy.simplegithub.databinding.ActivityMainBinding
import com.timothy.simplegithub.ui.UserContract.State
import com.timothy.simplegithub.ui.ext.viewBinding
import com.timothy.simplegithub.ui.model.UserModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserContract.View {

    @Inject
    lateinit var presenter: UserContract.Presenter

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeState()
        observeTextChanges()
    }

    private fun observeState() = presenter.getState().observe(this, Observer(::render))

    private fun observeTextChanges() = presenter.observeTextChanges(
        binding.searchView.getTextChangesObservable()
    )

    override fun render(state: State) = when (state) {
        is State.Empty -> renderEmptyState()
        is State.Success -> renderSuccessState(state.data)
        is State.Error -> renderErrorState(state.message)
    }

    private fun renderEmptyState() {

    }

    private fun renderSuccessState(data: List<UserModel>) {

    }

    private fun renderErrorState(message: String) {
        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_SHORT).show()
    }
}