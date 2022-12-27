package com.example.nazarenopathfinder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "path_item_table")
class PathItem (
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "source") var source: String,
    @ColumnInfo(name = "destination") var destination: String,
    @ColumnInfo(name = "description") var description: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)