package com.example.diarysnap.domain.model

data class DiaryEntry(
    val id: Long = 0L,
    val title: String,
    val text: String,
    val mood: String,
    val createdAt: Long,
    val imageUrl: String?
)

