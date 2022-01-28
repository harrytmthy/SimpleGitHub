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

package com.timothy.simplegithub.data.di

import com.timothy.simplegithub.data.UserDataRepository
import com.timothy.simplegithub.data.source.UserDataSource
import com.timothy.simplegithub.data.source.local.LocalUserDataSource
import com.timothy.simplegithub.data.source.network.NetworkUserDataSource
import com.timothy.simplegithub.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UserSearchModule {

    @Provides
    @Singleton
    @Local
    fun provideLocalUserDataSource(source: LocalUserDataSource): UserDataSource = source

    @Provides
    @Singleton
    @Network
    fun provideNetworkUserDataSource(source: NetworkUserDataSource): UserDataSource = source

    @Provides
    @Singleton
    fun provideUserRepository(repository: UserDataRepository): UserRepository = repository
}