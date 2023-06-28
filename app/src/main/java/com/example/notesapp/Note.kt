package com.example.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//data class Note (
//    @PrimaryKey(autoGenerate = true) val id: Int? = null,
//    var date:String,
//    var title:String,
//    var description:String
//)
@Entity(tableName = "note")
class Note(
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String

) {

    @PrimaryKey(autoGenerate = true)
    var id=0
}