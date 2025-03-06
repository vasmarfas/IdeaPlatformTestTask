package ru.vasmarfas.ideaplatformtesttask.data

import kotlinx.coroutines.flow.Flow
import ru.vasmarfas.ideaplatformtesttask.data.db.ItemsTable
import ru.vasmarfas.ideaplatformtesttask.data.db.NewItem

interface MainRepositoryInterface {
    suspend fun getItems(): List<ItemsTable>
    suspend fun addItem(item: NewItem)
    suspend fun updateItem(item: ItemsTable)
    suspend fun updateAmountById(id: Int, newAmount: Int)
    suspend fun deleteItem(item: ItemsTable)
    suspend fun deleteItemById(id: Int)
    fun getItemsFlow(): Flow<List<ItemsTable>>
}