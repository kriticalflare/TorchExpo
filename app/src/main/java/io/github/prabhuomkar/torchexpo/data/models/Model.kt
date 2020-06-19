package io.github.prabhuomkar.torchexpo.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "models", foreignKeys = [ForeignKey(
        entity = Task::class, parentColumns = ["id"], childColumns = ["taskId"],
        onDelete = CASCADE
    )]
)
data class Model(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val paperLink: String,
    val sourceLink: String,
    val downloadLink: String,
    val imageLink: String,
    val size: Int,
    val taskId: Int
)
