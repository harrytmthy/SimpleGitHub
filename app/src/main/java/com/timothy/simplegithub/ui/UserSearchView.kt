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

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import com.jakewharton.rxbinding4.widget.textChanges
import com.timothy.simplegithub.databinding.ViewUserSearchBinding
import com.timothy.simplegithub.ui.ext.viewBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class UserSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by viewBinding(ViewUserSearchBinding::inflate)

    init {
        ViewCompat.setElevation(this, DEFAULT_ELEVATION)
    }

    fun getTextChangesObservable() = binding.searchTextView.textChanges()
        .debounce(TEXT_CHANGES_DELAY, TimeUnit.MILLISECONDS)
        .skip(SKIPPED_TEXT)
        .map { it.toString() }
        .observeOn(AndroidSchedulers.mainThread())

    companion object {
        private const val DEFAULT_ELEVATION = 16f
        private const val TEXT_CHANGES_DELAY = 500L
        private const val SKIPPED_TEXT = 1L
    }
}