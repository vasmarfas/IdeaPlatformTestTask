package ru.vasmarfas.ideaplatformtesttask.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ItemsTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "time")
    val time: Long,
    @ColumnInfo(name = "tags")
    val tags: List<String>,
    @ColumnInfo(name = "amount")
    val amount: Int,
) {
    companion object {

        fun mock() = ItemsTable(
            id = 1,
            name = "Название продукта",
            time = 1633219200000,
            tags = listOf("Тэг1", "Тэг2", "Тэг3", "Длинный тэг", "мой тэг новый"),
            amount = 5
        )

        fun mockList() = listOf(
            mock(),
            mock().copy(
                id = 2,
                name = "Второе название",
            )
        )
    }
}