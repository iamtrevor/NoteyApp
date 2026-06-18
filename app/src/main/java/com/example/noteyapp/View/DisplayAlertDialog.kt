package com.example.noteyapp.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.noteyapp.RoomDB.Note
import com.example.noteyapp.ViewModel.NoteViewModel


@Composable
fun DisplayAlertDialog(noteViewModel: NoteViewModel, showDialog : Boolean, onDismiss : () -> Unit) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color.Blue) }


    if(showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Add Note") },

            text = {
                Column {

                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                //color picker composable
                    MyColorPicker(
                        selectedColor = selectedColor,
                        onSelectedColor = {selectedColor = it}
                    )


                }
            },

            confirmButton = {

                Button(

                    onClick = {
                        var note = Note(
                            0,
                            title,
                            description,
                            selectedColor.toArgb()
                        )

                        //insert the note into room database
                        noteViewModel.insertNotes(note)

                    }

                ) {
                    Text(text = "Add")
                }
            },

            dismissButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text(text = "Cancel")
                }
            }


        )
    }

}