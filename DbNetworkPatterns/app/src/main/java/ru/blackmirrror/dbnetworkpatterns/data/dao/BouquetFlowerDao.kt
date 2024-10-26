package ru.blackmirrror.dbnetworkpatterns.task12.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ru.blackmirrror.dbnetworkpatterns.task12.model.BouquetFlower
import ru.blackmirrror.dbnetworkpatterns.task12.model.Flower

@Dao
interface BouquetFlowerDao {
    @Query("SELECT * FROM bouquet_flower WHERE bouquet_id = :bouquetId")
    suspend fun getBouquetFlowers(bouquetId: Int): List<BouquetFlower>

    @Query("SELECT * FROM flowers WHERE flower_id = :id")
    suspend fun getFlowerById(id: Int): Flower

    @Update
    suspend fun updateFlower(flower: Flower)

    @Transaction
    suspend fun buyBouquet(bouquetId: Int) {
        val bouquetFlowers: List<BouquetFlower> = getBouquetFlowers(bouquetId)

        for ((_, flowerId, quantity) in bouquetFlowers) {
            val flower: Flower = getFlowerById(flowerId)
            flower.stock -= quantity
            check(flower.stock >= 0) { "Not enough flowers in stock" }
            updateFlower(flower)
        }
    }
}