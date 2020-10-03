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

object Dependencies {
    val DEPENDENCIES = setOf(
        Dependency.Implementation(Libs.APPCOMPAT, Versions.APPCOMPAT),
        Dependency.Implementation(Libs.CONSTRAINT_LAYOUT, Versions.CONSTRAINT_LAYOUT),
        Dependency.Implementation(Libs.CORE_KTX, Versions.CORE_KTX),
        Dependency.Implementation(Libs.COROUTINES, Versions.COROUTINES),
        Dependency.Implementation(Libs.GLIDE, Versions.GLIDE),
        Dependency.Implementation(Libs.HILT_ANDROID, Versions.HILT),
        Dependency.Implementation(Libs.HILT_VIEWMODEL, Versions.HILT_KTX),
        Dependency.Implementation(Libs.KOTLIN_STDLIB, Versions.KOTLIN),
        Dependency.Implementation(Libs.LIFECYCLE_LIVEDATA_KTX, Versions.LIFECYCLE),
        Dependency.Implementation(Libs.LIFECYCLE_VIEWMODEL_KTX, Versions.LIFECYCLE),
        Dependency.Implementation(Libs.MATERIAL, Versions.MATERIAL),
        Dependency.Implementation(Libs.MULTIDEX, Versions.MULTIDEX),
        Dependency.Implementation(Libs.OKHTTP, Versions.OKHTTP),
        Dependency.Implementation(Libs.RECYCLERVIEW, Versions.RECYCLERVIEW),
        Dependency.Implementation(Libs.RETROFIT, Versions.RETROFIT),
        Dependency.Implementation(Libs.ROOM_RUNTIME, Versions.ROOM),
        Dependency.Implementation(Libs.ROOM_KTX, Versions.ROOM),
        Dependency.Implementation(Libs.RX_BINDING, Versions.RX_BINDING),
        Dependency.Implementation(Libs.SERIALIZATION, Versions.SERIALIZATION),
        Dependency.Implementation(Libs.SERIALIZATION_CONVERTER, Versions.SERIALIZATION_CONVERTER),
        Dependency.Implementation(Libs.THREETENABP, Versions.THREETENABP),
        Dependency.Implementation(Libs.TIMBER, Versions.TIMBER),
        Dependency.Kapt(Libs.GLIDE_COMPILER, Versions.GLIDE),
        Dependency.Kapt(Libs.HILT_COMPILER, Versions.HILT),
        Dependency.Kapt(Libs.HILT_COMPILER_KTX, Versions.HILT_KTX),
        Dependency.Kapt(Libs.LIFECYCLE_COMPILER, Versions.LIFECYCLE),
        Dependency.Kapt(Libs.ROOM_COMPILER, Versions.ROOM),
        Dependency.TestImplementation(Libs.COROUTINES_TEST, Versions.COROUTINES),
        Dependency.TestImplementation(Libs.HILT_TESTING, Versions.HILT),
        Dependency.TestImplementation(Libs.JUNIT, Versions.JUNIT),
        Dependency.TestImplementation(Libs.MOCKITO_CORE, Versions.MOCKITO_CORE),
        Dependency.TestImplementation(Libs.MOCKITO_KOTLIN, Versions.MOCKITO_KOTLIN)
    )

    fun from(name: String, version: String) = "$name:$version"
}