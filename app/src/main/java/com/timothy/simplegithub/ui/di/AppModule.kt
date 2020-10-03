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

package com.timothy.simplegithub.ui.di

import android.content.Context
import com.timothy.simplegithub.data.db.AppDatabase
import com.timothy.simplegithub.domain.di.ApplicationScope
import com.timothy.simplegithub.domain.di.DefaultDispatcher
import com.timothy.simplegithub.domain.di.MainThreadHandler
import com.timothy.simplegithub.domain.internal.SimpleGitHubHandler
import com.timothy.simplegithub.domain.internal.SimpleGitHubMainHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @ApplicationScope
    @Singleton
    @Provides
    fun providesApplicationScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @Singleton
    @Provides
    @MainThreadHandler
    fun provideMainThreadHandler(): SimpleGitHubHandler = SimpleGitHubMainHandler()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.buildDatabase(context)
}