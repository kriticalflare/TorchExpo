package io.github.prabhuomkar.torchexpo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "models")
data class Model(
    @PrimaryKey
    val name: String,
    val description: String,
    val paperLink: String,
    val sourceLink: String,
    val downloadLink: String,
    val imageLink: String,
    val playgroundActivity: String,
    val size: Int
)
