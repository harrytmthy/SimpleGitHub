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

package com.timothy.simplegithub.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timothy.simplegithub.databinding.ItemUserCardBinding
import com.timothy.simplegithub.ui.model.UserModel
import com.timothy.simplegithub.ui.viewholder.UserViewHolder

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var items = mutableListOf<UserModel>()

    fun setUsers(users: List<UserModel>) {
        items = users.toMutableList()
        notifyDataSetChanged()
    }

    fun addUsers(users: List<UserModel>) {
        val startPosition = items.size
        items.addAll(users)
        notifyItemInserted(startPosition)
    }

    fun removeAllUsers() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        ItemUserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount() = items.size
}