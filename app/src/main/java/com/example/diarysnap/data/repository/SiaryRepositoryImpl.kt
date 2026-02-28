package com.example.diarysnap.data.repository

import com.example.diarysnap.data.local.DiaryDao
import com.example.diarysnap.data.local.DiaryEntryEntity
import com.example.diarysnap.domain.model.DiaryEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DiaryRepositoryImpl(
    private val dao: DiaryDao
) : DiaryRepository {

    override fun observeAll(): Flow<List<DiaryEntry>> =
        dao.observeAll().map { list -> list.map { it.toDomain() } }

    override fun observeById(id: Long): Flow<DiaryEntry?> =
        dao.observeById(id).map { it?.toDomain() }

    override suspend fun add(entry: DiaryEntry): Long =
        dao.insert(entry.toEntity())

    override suspend fun delete(id: Long) {
        dao.deleteById(id)
    }

    private fun DiaryEntryEntity.toDomain() = DiaryEntry(
        id = id,
        title = title,
        text = text,
        mood = mood,
        createdAt = createdAt,
        imageUrl = imageUrl
    )

    private fun DiaryEntry.toEntity() = DiaryEntryEntity(
        id = id,
        title = title,
        text = text,
        mood = mood,
        createdAt = createdAt,
        imageUrl = imageUrl
    )
}

