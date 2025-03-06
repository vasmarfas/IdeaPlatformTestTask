package ru.vasmarfas.ideaplatformtesttask.data.db

import androidx.room.ColumnInfo

data class NewItem(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "time")
    val time: Long,
    @ColumnInfo(name = "tags")
    val tags: List<String>,
    @ColumnInfo(name = "amount")
    val amount: Int,
)
