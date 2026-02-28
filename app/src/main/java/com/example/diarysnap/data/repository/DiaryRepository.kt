package com.example.diarysnap.data.repository

import com.example.diarysnap.domain.model.DiaryEntry
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {
    fun observeAll(): Flow<List<DiaryEntry>>
    fun observeById(id: Long): Flow<DiaryEntry?>
    suspend fun add(entry: DiaryEntry): Long
    suspend fun delete(id: Long)
}

