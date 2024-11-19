package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract
import com.example.domain.repository.path.PathModel

@Entity(
    tableName = RoomContract.tablePaths
)
data class PathEntity (
    @PrimaryKey(autoGenerate = false)
    val idPath: Int,
    val title: String,
    val image: String
) {
    companion object {
        fun toPathEntity(path: PathModel) = PathEntity(
            idPath = path.idPath,
            title = path.title,
            image = path.image
        )
    }

    fun toPath() = PathModel(
        idPath = idPath,
        title = title,
        image = image
    )
}