package com.example.noteyapp.Repository

import androidx.lifecycle.LiveData
import com.example.noteyapp.RoomDB.Note
import com.example.noteyapp.RoomDB.NotesDao



class NotesRepository(private val notesDao : NotesDao) {

    //repository acts as a single source of truth
    //for data in your app
    val getAllNotes : LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insertNote(note : Note){
        notesDao.insert(note)
    }
}


