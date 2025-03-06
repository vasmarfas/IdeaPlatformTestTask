package ru.vasmarfas.ideaplatformtesttask.presentation.screens.main_screen

import ru.vasmarfas.ideaplatformtesttask.data.db.ItemsTable

object MainScreenContract {
    data class MainScreenState(
        val items: List<ItemsTable> = emptyList(),
        val isLoading: Boolean = true,
    )

    sealed interface Action {
        data class UpdateItemCount(val id: Int, val newCount: Int) : Action
        data class DeleteItem(val id: Int) : Action
    }
}