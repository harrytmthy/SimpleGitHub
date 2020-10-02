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

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> Activity.viewBinding(inflater: (LayoutInflater) -> T) = lazy {
    inflater.invoke(layoutInflater)
}

fun <T : ViewBinding> ViewGroup.viewBinding(
    inflater: (LayoutInflater, ViewGroup, Boolean) -> T
) = lazy {
    inflater.invoke(LayoutInflater.from(context), this, true)
}