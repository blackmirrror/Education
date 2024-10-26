package ru.blackmirrror.dbnetworkpatterns.task12.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "bouquet_flower",
    primaryKeys = ["bouquet_id", "flower_id"],
    foreignKeys = [
        ForeignKey(
            entity = Bouquet::class,
            parentColumns = ["bouquet_id"],
            childColumns = ["bouquet_id"]
        ),
        ForeignKey(
            entity = Flower::class,
            parentColumns = ["flower_id"],
            childColumns = ["flower_id"]
        )
    ]
)
data class BouquetFlower(
    @ColumnInfo(name = "bouquet_id")
    val bouquetId: Int,
    @ColumnInfo(name = "flower_id")
    val flowerId: Int,
    val quantity: Int,
)