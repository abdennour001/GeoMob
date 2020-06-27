package com.example.geomob.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "country")
data class Country (
    @PrimaryKey(autoGenerate = true) var id: Int,


    @ColumnInfo(name="name") var name: String,
    @ColumnInfo(name="description") var description: String,
    @ColumnInfo(name="flag") var flag: String,
    @ColumnInfo(name="surface") var surface: Double,
    @ColumnInfo(name="population") var population: Double,
    @ColumnInfo(name="mainResources") var mainResources: List<String>,
    @ColumnInfo(name="mainPersonalities") var mainPersonalities: List<String>,
    @ColumnInfo(name="images") var images: List<String>,
    @ColumnInfo(name="anthem_url") var anthemUrl: String

) : Serializable {
    constructor():this(0, "", "", "", 0.0, 0.0, listOf(""), listOf(""), listOf(""), "")
}
