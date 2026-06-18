package com.example.noteyapp.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NotesDB : RoomDatabase() {

    //instance of the dao
    abstract val notesDao : NotesDao

    //using the singleton pattern to create a single instance of the db


    companion object {

        //volatile prevents race conditions in multithreading
        @Volatile
        private var INSTANCE : NotesDB? = null


        fun getInstance(context: Context) : NotesDB {
            //synchronized ensures only one thread can access the db at a time
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    //create a db object
                    instance = Room.databaseBuilder(
                        context = context.applicationContext,
                        NotesDB::class.java,
                        "notes_db"
                    ).build()

                }
                INSTANCE = instance
                return instance
            }
        }

    }







}