package com.example.noteyapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteyapp.Repository.NotesRepository
import com.example.noteyapp.RoomDB.Note
import kotlinx.coroutines.launch


//view model manages all business logic related to the view
class NoteViewModel(val repository: NotesRepository) : ViewModel() {

    val allNotes : LiveData<List<Note>> = repository.getAllNotes

    fun insertNotes(note: Note){
        //viewModelScope a coroutine scope tied to the viewModel LifeCycle. Any coroutines launched within it are automatically canceled when the viewModel is cleared
       //launch a coroutine builder that launch a new coroutine without blocking the current thread
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }





}