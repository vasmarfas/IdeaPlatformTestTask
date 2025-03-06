package ru.vasmarfas.ideaplatformtesttask.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM item")
    fun getAll(): Flow<List<ItemsTable>>

    @Insert(entity = ItemsTable::class)
    suspend fun insert(newItem: NewItem)

    @Delete
    suspend fun delete(item: ItemsTable)

    @Query("DELETE FROM item WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Update
    suspend fun update(item: ItemsTable)

    @Query("UPDATE item SET amount = :newAmount WHERE id = :id")
    suspend fun updateAmountById(id: Int, newAmount: Int)
}
