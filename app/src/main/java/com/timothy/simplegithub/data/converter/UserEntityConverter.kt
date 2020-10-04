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

package com.timothy.simplegithub.data.converter

import androidx.room.TypeConverter
import com.timothy.simplegithub.data.model.UserEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object UserEntityConverter {

    @TypeConverter
    fun jsonToUserEntities(jsonString: String) = Json.decodeFromString<List<UserEntity>>(jsonString)

    @TypeConverter
    fun userEntitiesToJson(userEntities: List<UserEntity>) = Json.encodeToString(userEntities)
}