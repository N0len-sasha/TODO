package com.example.todo.model


import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity (tableName = "folder_table")
data class Folder(
    @PrimaryKey(autoGenerate = true)
    val idFolder: Int,
    val nameFolder: String
):Parcelable


