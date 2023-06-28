package com.example.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase :RoomDatabase(){
    abstract fun getNotesDao():NoteDao

    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase?=null

        fun getDatabase(context: Context):NoteDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java,"notesDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
