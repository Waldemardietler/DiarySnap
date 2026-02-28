package com.example.diarysnap

import android.content.Context
import androidx.room.Room
import com.example.diarysnap.data.local.DiaryDatabase
import com.example.diarysnap.data.remote.UnsplashClient
import com.example.diarysnap.data.repository.AuthRepository
import com.example.diarysnap.data.repository.AuthRepositoryImpl
import com.example.diarysnap.data.repository.DiaryRepository
import com.example.diarysnap.data.repository.DiaryRepositoryImpl

object AppServices {

    private var db: DiaryDatabase? = null

    val authRepository: AuthRepository by lazy { AuthRepositoryImpl() }

    fun diaryRepository(context: Context): DiaryRepository {
        val database = db ?: Room.databaseBuilder(
            context.applicationContext,
            DiaryDatabase::class.java,
            "diary.db"
        ).build().also { db = it }

        return DiaryRepositoryImpl(database.diaryDao())
    }

    val unsplashApi = UnsplashClient.api
}

