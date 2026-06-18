package com.example.noteyapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import com.example.noteyapp.Model.Repository.NotesRepository
import com.example.noteyapp.Repository.NotesRepository

//a viewModel factory is used to create instances
//of a viewModel that has specific parameters
class NoteViewModelFactory(val repository: NotesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}