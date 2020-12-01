package com.example.jesusapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.jesusapp.db.BibleDataBase
import com.example.jesusapp.db.UserDao

import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    fun providesRoom( @ApplicationContext appContext : Context) : BibleDataBase {
        return Room
            .databaseBuilder(appContext, BibleDataBase::class.java, "database-users")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: BibleDataBase): UserDao {
        return userDatabase.userDao()
    }

}