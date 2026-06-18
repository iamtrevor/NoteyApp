package com.example.noteyapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
//import com.example.noteyapp.Model.Repository.NotesRepository
import com.example.noteyapp.Repository.NotesRepository
import com.example.noteyapp.RoomDB.NotesDB
import com.example.noteyapp.View.DisplayAlertDialog
import com.example.noteyapp.View.DisplayNoteListItem

import com.example.noteyapp.ViewModel.NoteViewModel
import com.example.noteyapp.ViewModel.NoteViewModelFactory
import com.example.noteyapp.ui.theme.NoteyAppTheme




class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        //database instance
        val db = NotesDB.getInstance(applicationContext)

        //repository
        val repository = NotesRepository(db.notesDao)

        //viewModelFactory
        val viewModelFactory = NoteViewModelFactory(repository)

        //viewModel
        val noteViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[NoteViewModel::class.java]


        enableEdgeToEdge()
        setContent {
            NoteyAppTheme {

                Scaffold(
                    floatingActionButton = { MYFAB(noteViewModel) }

                ) {
                    //display all records in room database
                    val notes by noteViewModel.allNotes.observeAsState(emptyList())

                    //observeAsState : Converts a livedata into state a state object
                    //that can be observed within a composable function.
                    DisplayNoteListItem(notes = notes)

                }



            }
        }
    }
}



@Composable
fun MYFAB(noteViewModel: NoteViewModel){

    //controlling the dialog appearance
    var showDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(noteViewModel = noteViewModel, showDialog = showDialog) {
        showDialog = false
    }

    FloatingActionButton(
        onClick = { showDialog = true },

        contentColor = Color.White,
        containerColor = Color.Blue
    ){

        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Note icon"
        )

    }

}