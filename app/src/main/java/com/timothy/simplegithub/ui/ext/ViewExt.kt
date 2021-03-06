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

package com.timothy.simplegithub.ui.ext

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.timothy.simplegithub.R
import com.timothy.simplegithub.ui.common.GlideApp

fun ImageView.loadUrl(url: String) = GlideApp.with(context)
    .load(url)
    .error(R.drawable.ic_default_avatar)
    .into(this)

fun RecyclerView.addOnNextPageListener(block: () -> Unit) = addOnScrollListener(
    object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (isScrolledToLast()) {
                block()
            }
        }
    }
)

private fun RecyclerView.isScrolledToLast() = (layoutManager as? LinearLayoutManager)?.run {
    findLastVisibleItemPosition() == itemCount - 1
} ?: false