package com.example.diarysnap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diarysnap.data.remote.UnsplashApi
import com.example.diarysnap.data.repository.DiaryRepository
import com.example.diarysnap.domain.model.DiaryEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateEntryViewModel(
    private val repo: DiaryRepository,
    private val api: UnsplashApi
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl: StateFlow<String?> = _imageUrl

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadImage(keyword: String) {
        if (keyword.isBlank()) {
            _error.value = "Bitte Mood/Keyword eingeben"
            return
        }
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val res = api.searchPhotos(query = keyword.trim(), perPage = 1)
                _imageUrl.value = res.results.firstOrNull()?.urls?.regular
                if (_imageUrl.value == null) _error.value = "Kein Bild gefunden"
            } catch (e: Exception) {
                _error.value = e.message ?: "Bild konnte nicht geladen werden"
            } finally {
                _loading.value = false
            }
        }
    }

    fun save(title: String, text: String, mood: String, onDone: () -> Unit) {
        if (title.isBlank() || text.isBlank()) {
            _error.value = "Titel und Text sind Pflicht"
            return
        }
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                repo.add(
                    DiaryEntry(
                        title = title.trim(),
                        text = text.trim(),
                        mood = mood.trim(),
                        createdAt = System.currentTimeMillis(),
                        imageUrl = _imageUrl.value
                    )
                )
                onDone()
            } catch (e: Exception) {
                _error.value = e.message ?: "Speichern fehlgeschlagen"
            } finally {
                _loading.value = false
            }
        }
    }
}

