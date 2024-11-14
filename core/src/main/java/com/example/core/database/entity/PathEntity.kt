package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.database.contract.RoomContract
import com.example.core.domain.repository.path.Path

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
        fun toPathEntity(path: Path) = PathEntity(
            idPath = path.idPath,
            title = path.title,
            image = path.image
        )
    }

    fun toPath() = Path(
        idPath = idPath,
        title = title,
        image = image
    )
}