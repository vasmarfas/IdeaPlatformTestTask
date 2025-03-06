package ru.vasmarfas.ideaplatformtesttask.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import ru.vasmarfas.ideaplatformtesttask.data.db.ItemsTable
import ru.vasmarfas.ideaplatformtesttask.data.db.NewItem
import ru.vasmarfas.ideaplatformtesttask.data.db.UserDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dao: UserDao,
) : MainRepositoryInterface {
    override suspend fun getItems(): List<ItemsTable> {
        return dao.getAll().first()
    }

    override suspend fun addItem(item: NewItem) {
        dao.insert(item)
    }

    override suspend fun updateItem(item: ItemsTable) {
        dao.update(item)
    }

    override suspend fun updateAmountById(id: Int, newAmount: Int) {
        dao.updateAmountById(id, newAmount)
    }

    override suspend fun deleteItem(item: ItemsTable) {
        dao.delete(item)
    }

    override suspend fun deleteItemById(id: Int) {
        dao.deleteById(id)
    }

    override fun getItemsFlow(): Flow<List<ItemsTable>> {
        return dao.getAll()
    }
}