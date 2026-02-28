package com.example.diarysnap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diarysnap.data.repository.DiaryRepository
import com.example.diarysnap.domain.model.DiaryEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repo: DiaryRepository
) : ViewModel() {

    fun entry(id: Long): Flow<DiaryEntry?> = repo.observeById(id)

    fun delete(id: Long, onDone: () -> Unit) {
        viewModelScope.launch {
            repo.delete(id)
            onDone()
        }
    }
}

