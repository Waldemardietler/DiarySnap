package com.example.diarysnap.viewmodel

import androidx.lifecycle.ViewModel
import com.example.diarysnap.data.repository.DiaryRepository
import com.example.diarysnap.domain.model.DiaryEntry
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    private val repo: DiaryRepository
) : ViewModel() {
    val entries: Flow<List<DiaryEntry>> = repo.observeAll()
}

